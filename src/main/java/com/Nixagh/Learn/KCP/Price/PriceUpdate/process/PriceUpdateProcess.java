package com.Nixagh.Learn.KCP.Price.PriceUpdate.process;

import com.Nixagh.Learn.KCP.Price.PriceUpdate.dto.PriceUpdateRequest;
import com.Nixagh.Learn.KCP.Price.PriceUpdate.dto.PriceUpdateResponse;
import com.Nixagh.Learn.common.dto.request.AbsRequest;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import com.Nixagh.Learn.common.process.AbsProcess;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class PriceUpdateProcess extends AbsProcess {

  public PriceUpdateResponse process(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {
    PriceUpdateRequest priceUpdateRequest = (PriceUpdateRequest) request;
    PriceUpdateResponse priceUpdateResponse = (PriceUpdateResponse) response;

    double priceOfWater = priceUpdateRequest.priceUpdateDto.priceOfWater;
    double priceOfElectric = priceUpdateRequest.priceUpdateDto.priceOfElectric;

    String userId = priceUpdateRequest.accessInfo.userId;

    Query query = new Query(Criteria.where("userId").is(userId));
    Update update = new Update();
    update.set("priceOfWater", priceOfWater);
    update.set("priceOfElectric", priceOfElectric);
    mongoTemplate.updateFirst(query, update, "Price");
    return priceUpdateResponse;
  }

  public PriceUpdateResponse createNewResponse() {
    return new PriceUpdateResponse();
  }
}
