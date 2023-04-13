package com.Nixagh.Learn.KCP.priceCalulation.priceCalDelete.process;

import com.Nixagh.Learn.KCP.priceCalulation.priceCalDelete.dto.PriceCalDeleteRequest;
import com.Nixagh.Learn.KCP.priceCalulation.priceCalDelete.dto.PriceCalDeleteResponse;
import com.Nixagh.Learn.common.dto.errorDto;
import com.Nixagh.Learn.common.dto.request.AbsRequest;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import com.Nixagh.Learn.common.process.AbsProcess;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class PriceCalDeleteProcess extends AbsProcess {
  @Override
  public PriceCalDeleteResponse process(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {
    PriceCalDeleteResponse roomDeleteResponse = (PriceCalDeleteResponse) response;
    PriceCalDeleteRequest roomDeleteRequest = (PriceCalDeleteRequest) request;

    String roomId = roomDeleteRequest.priceCalConditions.roomId;
    Query query = new Query();
    query.addCriteria(Criteria.where("roomId").is(roomId));
    try {
      mongoTemplate.remove(query, "PriceCalculator");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      roomDeleteResponse.addError(new errorDto("PRICE_CALCULATOR_DELETE", "Error processing request to delete"));
    }

    return roomDeleteResponse;
  }

  public PriceCalDeleteResponse createNewResponse() {
    return new PriceCalDeleteResponse();
  }
}
