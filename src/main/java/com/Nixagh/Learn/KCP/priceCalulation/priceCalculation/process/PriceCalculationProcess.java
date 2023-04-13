package com.Nixagh.Learn.KCP.priceCalulation.priceCalculation.process;

import com.Nixagh.Learn.KCP.priceCalulation.priceCalculation.dto.PriceCalculationRequest;
import com.Nixagh.Learn.KCP.priceCalulation.priceCalculation.dto.PriceCalculationResponse;
import com.Nixagh.Learn.KCP.priceCalulation.priceCalculation.dto.PriceCalculationRow;
import com.Nixagh.Learn.common.dto.errorDto;
import com.Nixagh.Learn.common.dto.request.AbsRequest;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import com.Nixagh.Learn.common.process.AbsProcess;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;

public class PriceCalculationProcess extends AbsProcess {
  @Override
  public PriceCalculationResponse process(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {
    PriceCalculationRequest priceCalculationRequest = (PriceCalculationRequest) request;
    PriceCalculationResponse priceCalculationResponse = (PriceCalculationResponse) response;

    String id = priceCalculationRequest.id;
    String userId = priceCalculationRequest.accessInfo.userId;

    Aggregation aggregation = Aggregation.newAggregation(
            Aggregation.match(new Criteria().andOperator(new Criteria("userId").is(userId), new Criteria("_id").is(new ObjectId(id)))),
            Aggregation.lookup("Price", "userId", "userId", "Price")
    );
    try {
      ArrayList<PriceCalculationRow> lst = new ArrayList<PriceCalculationRow>();
      mongoTemplate.aggregate(aggregation, "PriceCalculator", PriceCalculationRow.class).getMappedResults().forEach(item -> {
        PriceCalculationRow row = new PriceCalculationRow();
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
        row.totalRevenue = (row.totalMoney - row.deposit) < 0 ? 0 : (row.totalMoney - row.deposit);
        lst.add(row);
      });
      PriceCalculationRow result = new PriceCalculationRow(lst.get(0));

      if (result.deposit > result.totalMoney) {
        result.deposit -= result.totalMoney;
        lst.get(0).deposit = lst.get(0).totalMoney;
      } else result.deposit = 0;
      int temp1 = result.electricIndex.New;
      int temp2 = result.electricIndex.Old;
      int temp3 = result.waterIndex.New;
      int temp4 = result.waterIndex.Old;

      result.electricIndex.Old = result.electricIndex.New;
      result.waterIndex.Old = result.waterIndex.New;

      Update update = new Update();
      update.set("electricIndex.Old", result.electricIndex.Old);
      update.set("waterIndex.Old", result.waterIndex.Old);
      update.set("deposit", result.deposit);

      Query query = new Query();
      query.addCriteria(Criteria.where("_id").is(new ObjectId(id)));
      mongoTemplate.updateFirst(query, update, "PriceCalculator");
      lst.get(0).waterIndex.New = temp3;
      lst.get(0).waterIndex.Old = temp4;
      lst.get(0).electricIndex.New = temp1;
      lst.get(0).electricIndex.Old = temp2;
      priceCalculationResponse.rows = lst.get(0);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      priceCalculationResponse.addError(new errorDto("PRICE_CALCULATION", "Price calculation failed"));
    }
    return priceCalculationResponse;
  }

  @Override
  public PriceCalculationResponse createNewResponse() {
    return new PriceCalculationResponse();
  }
}
