package com.Nixagh.Learn.KCP.Price.PriceSearch.process;

import com.Nixagh.Learn.KCP.Price.PriceSearch.dto.PriceSearchRequest;
import com.Nixagh.Learn.KCP.Price.PriceSearch.dto.PriceSearchResponse;
import com.Nixagh.Learn.KCP.Price.PriceSearch.dto.PriceSearchRow;
import com.Nixagh.Learn.common.dto.request.AbsRequest;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import com.Nixagh.Learn.common.process.AbsProcess;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class PriceSearchProcess extends AbsProcess {

  public PriceSearchResponse process(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {
    PriceSearchRequest priceSearchRequest = (PriceSearchRequest) request;
    PriceSearchResponse priceSearchResponse = (PriceSearchResponse) response;

    String userId = priceSearchRequest.accessInfo.userId;
    Query query = new Query(Criteria.where("userId").is(userId));

    priceSearchResponse.row = mongoTemplate.findOne(query, PriceSearchRow.class, "Price");
    return priceSearchResponse;
  }

  public PriceSearchResponse createNewResponse() {
    return new PriceSearchResponse();
  }
}
