package com.Nixagh.Learn.KCP.priceCalulation.priceCalUpdate.process;

import com.Nixagh.Learn.KCP.priceCalulation.priceCalUpdate.dto.PriceCalUpdateRequest;
import com.Nixagh.Learn.KCP.priceCalulation.priceCalUpdate.dto.PriceCalUpdateResponse;
import com.Nixagh.Learn.common.dto.ElectricIndex;
import com.Nixagh.Learn.common.dto.WaterIndex;
import com.Nixagh.Learn.common.dto.errorDto;
import com.Nixagh.Learn.common.dto.request.AbsRequest;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import com.Nixagh.Learn.common.process.AbsProcess;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class PriceCalUpdateProcess extends AbsProcess {
  @Override
  public PriceCalUpdateResponse process(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {
    PriceCalUpdateRequest priceCalUpdateRequest = (PriceCalUpdateRequest) request;
    PriceCalUpdateResponse priceCalUpdateResponse = (PriceCalUpdateResponse) response;

    String id = priceCalUpdateRequest.priceCalUpdateDto.id;
    String userId = priceCalUpdateRequest.accessInfo.userId;
    int Status = priceCalUpdateRequest.priceCalUpdateDto.Status;

    Query query = new Query();
    query.addCriteria(Criteria.where("userId").is(userId));
    query.addCriteria(Criteria.where("_id").is(new ObjectId(id)));

    Update update = new Update();
    if (Status != -1) update.set("Status", Status);
    else {
      ElectricIndex indexOfElectric = priceCalUpdateRequest.priceCalUpdateDto.electricIndex;
      WaterIndex indexOfWater = priceCalUpdateRequest.priceCalUpdateDto.waterIndex;
      double deposit = priceCalUpdateRequest.priceCalUpdateDto.deposit;

      update.set("electricIndex.Old", indexOfElectric.Old);
      update.set("electricIndex.New", indexOfElectric.New);
      update.set("waterIndex.Old", indexOfWater.Old);
      update.set("waterIndex.New", indexOfWater.New);
      update.set("deposit", deposit);
    }
    try {
      mongoTemplate.updateFirst(query, update, "PriceCalculator");
    } catch (Exception e) {
      priceCalUpdateResponse.addError(new errorDto("PRICE_CREATE", "Price create failed"));
    }
    return priceCalUpdateResponse;
  }

  @Override
  public PriceCalUpdateResponse createNewResponse() {
    return new PriceCalUpdateResponse();
  }
}
