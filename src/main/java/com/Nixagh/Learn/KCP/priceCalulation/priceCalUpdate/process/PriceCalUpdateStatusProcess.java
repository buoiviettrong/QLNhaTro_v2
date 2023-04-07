package com.Nixagh.Learn.KCP.priceCalulation.priceCalUpdate.process;

import com.Nixagh.Learn.KCP.priceCalulation.priceCalUpdate.dto.PriceCalUpdateStatusRequest;
import com.Nixagh.Learn.KCP.priceCalulation.priceCalUpdate.dto.PriceCalUpdateStatusResponse;
import com.Nixagh.Learn.common.dto.errorDto;
import com.Nixagh.Learn.common.dto.request.AbsRequest;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import com.Nixagh.Learn.common.process.AbsProcess;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class PriceCalUpdateStatusProcess extends AbsProcess {
  @Override
  public PriceCalUpdateStatusResponse process(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {
    PriceCalUpdateStatusResponse priceCalUpdateStatusResponse = (PriceCalUpdateStatusResponse) response;
    PriceCalUpdateStatusRequest priceCalUpdateStatusRequest = (PriceCalUpdateStatusRequest) request;

    String roomName = priceCalUpdateStatusRequest.priceCalUpdateStatusConditions.roomName;
    int status = priceCalUpdateStatusRequest.priceCalUpdateStatusConditions.status;

    Query query = new Query();
    query.addCriteria(Criteria.where("roomName").is(roomName));
    Update update = new Update();
    update.set("Status", status);
    try {
      System.out.println(query);
      mongoTemplate.updateFirst(query, update, "PriceCalculator");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      priceCalUpdateStatusResponse.addError(new errorDto("PRICE_CAL_UPDATE_STATUS", "Could not update price cal status"));
    }
    return priceCalUpdateStatusResponse;
  }
  public PriceCalUpdateStatusResponse createNewResponse() {
    return new PriceCalUpdateStatusResponse();
  }
}
