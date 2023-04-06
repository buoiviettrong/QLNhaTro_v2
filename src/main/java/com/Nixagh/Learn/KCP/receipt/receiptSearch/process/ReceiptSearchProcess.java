package com.Nixagh.Learn.KCP.receipt.receiptSearch.process;

import com.Nixagh.Learn.KCP.receipt.receiptSearch.dto.*;
import com.Nixagh.Learn.common.dto.DateDto;
import com.Nixagh.Learn.common.dto.PriceDto;
import com.Nixagh.Learn.common.dto.request.AbsRequest;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import com.Nixagh.Learn.common.process.AbsProcess;
import com.Nixagh.Learn.common.utilities.ConvertDate;
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
    Criteria criteriaOr = new Criteria();
    Criteria criteriaAnd = new Criteria();
    ArrayList<Criteria> criteriaList = new ArrayList<Criteria>();
    if(id != null && !id.equals("")) query.addCriteria(Criteria.where("id").is(id));
    else {
      if (price.start != 0) criteriaList.add(new Criteria("totalRevenue").gte(price.start));
      if (price.end != 0) criteriaList.add(new Criteria("totalRevenue").lte(price.end));
      if (date.start != null && !date.start.equals("")) criteriaList.add(new Criteria("timestamp").gte(ConvertDate.convert(date.start, "yyyy-MM-dd", "dd-MM-yyyy")));
      if (date.end != null && !date.end.equals("")) criteriaList.add(new Criteria("timestamp").lte(ConvertDate.convert(date.end, "yyyy-MM-dd", "dd-MM-yyyy")));
      if (userId != null && !userId.equals("")) criteriaList.add(new Criteria("userId").is(userId));
      if (search != null && !search.equals(""))
        criteriaOr = new Criteria().orOperator(
                Criteria.where("roomName").regex(search, "i"),
                Criteria.where("customerName").in(search));
      query.addCriteria(criteriaOr.andOperator(criteriaList));
    }
    System.out.println(query);
    receiptSearchResponse.rows = (ArrayList<ReceiptSearchRows>) mongoTemplate.find(query, ReceiptSearchRows.class, "Receipt");
    return receiptSearchResponse;
  }
  @Override
  public ReceiptSearchResponse createNewResponse() { return new ReceiptSearchResponse(); }
}
