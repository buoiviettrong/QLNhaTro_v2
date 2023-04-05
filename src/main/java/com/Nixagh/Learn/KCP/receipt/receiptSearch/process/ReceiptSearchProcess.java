package com.Nixagh.Learn.KCP.receipt.receiptSearch.process;

import com.Nixagh.Learn.KCP.receipt.receiptSearch.dto.*;
import com.Nixagh.Learn.common.dto.DateDto;
import com.Nixagh.Learn.common.dto.PriceDto;
import com.Nixagh.Learn.common.dto.request.AbsRequest;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import com.Nixagh.Learn.common.process.AbsProcess;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;

public class ReceiptSearchProcess extends AbsProcess {
  @Override
  public ReceiptSearchResponse process(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {
    ReceiptSearchRequest receiptSearchRequest = (ReceiptSearchRequest) request;
    ReceiptSearchResponse receiptSearchResponse = (ReceiptSearchResponse) response;

    String id = receiptSearchRequest.receiptSearchConditions.id;
    String userId = receiptSearchRequest.accessInfo.userId;
    String search = receiptSearchRequest.receiptSearchConditions.search;
    PriceDto price = receiptSearchRequest.receiptSearchConditions.price;
    DateDto date = receiptSearchRequest.receiptSearchConditions.date;

    Query query = new Query();

    if(id != null && !id.equals("")) query.addCriteria(Criteria.where("id").is(id));
    else {
      if (price.start != 0) query.addCriteria(Criteria.where("r_total").gte(price.start));
      if (price.end != 0) query.addCriteria(Criteria.where("r_total").lte(price.end));
      if (date.start != null && !date.start.equals("")) query.addCriteria(Criteria.where("timestamp").gte(date.start));
      if (date.end != null && !date.end.equals("")) query.addCriteria(Criteria.where("timestamp").lte(date.end));
      if (id != null && !id.equals("")) query.addCriteria(Criteria.where("_id").is(id));
      if(search != null && !search.equals(""))
        query.addCriteria(new Criteria().orOperator(
                Criteria.where("customerName").in(search),
                Criteria.where("roomName").regex(search, "i")));
    }
    if (userId != null && !userId.equals("")) query.addCriteria(Criteria.where("userId").is(userId));
    System.out.println(query);
    receiptSearchResponse.rows = (ArrayList<ReceiptSearchRows>) mongoTemplate.find(query, ReceiptSearchRows.class, "Receipt");
    return receiptSearchResponse;
  }
  @Override
  public ReceiptSearchResponse createNewResponse() { return new ReceiptSearchResponse(); }
}
