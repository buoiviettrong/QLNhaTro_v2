package com.Nixagh.Learn.KCP.priceCalulation.priceCalSearch.process;

import com.Nixagh.Learn.KCP.priceCalulation.priceCalSearch.dto.PriceCalSearchRequest;
import com.Nixagh.Learn.KCP.priceCalulation.priceCalSearch.dto.PriceCalSearchResponse;
import com.Nixagh.Learn.KCP.priceCalulation.priceCalSearch.dto.PriceCalSearchRow;
import com.Nixagh.Learn.common.dto.errorDto;
import com.Nixagh.Learn.common.dto.request.AbsRequest;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import com.Nixagh.Learn.common.process.AbsProcess;
import com.Nixagh.Learn.common.utilities.ConvertDate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOptions;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Date;

public class PriceCalSearchProcess extends AbsProcess {
  @Override
  public PriceCalSearchResponse process(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {
    PriceCalSearchRequest priceCalSearchRequest = (PriceCalSearchRequest) request;
    PriceCalSearchResponse priceCalSearchResponse = (PriceCalSearchResponse) response;

    String roomName = priceCalSearchRequest.priceCalSearchConditions.roomName;
    int roomStatus = priceCalSearchRequest.priceCalSearchConditions.roomStatus;
    String userId = priceCalSearchRequest.accessInfo.userId;

    var roomName_ = new Criteria();
    var roomStatus_ = new Criteria();
    var userId_ = new Criteria("userId").is(userId);

    if(roomName != null && !roomName.isEmpty()) roomName_ = new Criteria("roomName").regex(roomName, "i");
    if(roomStatus != -1) roomStatus_ = new Criteria("Status").is(roomStatus);

    Aggregation aggregation =  Aggregation.newAggregation(
            Aggregation.match(new Criteria().andOperator(roomName_, roomStatus_, userId_)),
            Aggregation.lookup("Price", "userId", "userId", "Price")
    );
    ArrayList<PriceCalSearchRow> lst = new ArrayList<PriceCalSearchRow>();
    mongoTemplate.aggregate(aggregation,"PriceCalculator", PriceCalSearchRow.class).getMappedResults().forEach(item -> {
      PriceCalSearchRow row = new PriceCalSearchRow();
      row.id = item.id;
      row.roomName = item.roomName;
      row.roomPrice = item.roomPrice;
      row.electricIndex = item.electricIndex;
      row.waterIndex = item.waterIndex;
      row.Price = item.Price;
      row.Status = item.Status;
      row.totalMoneyOfElectric = (item.electricIndex.New - item.electricIndex.Old) * item.Price.get(0).priceOfElectric;
      row.totalMoneyOfWater = (item.waterIndex.New - item.waterIndex.Old) * item.Price.get(0).priceOfWater;
      row.totalMoney = row.totalMoneyOfElectric + row.totalMoneyOfWater + row.roomPrice;
      row.deposit = item.deposit;
      row.totalRevenue = row.totalMoney - row.deposit;
      lst.add(row);
    });
    priceCalSearchResponse.rows = lst;
    return priceCalSearchResponse;
  }
  @Override
  public PriceCalSearchResponse createNewResponse() {
    return new PriceCalSearchResponse();
  }
}
