package com.Nixagh.Learn.KCP.customer.customerSearch.process;

import com.Nixagh.Learn.KCP.customer.customerSearch.dto.CustomerSearchRequest;
import com.Nixagh.Learn.KCP.customer.customerSearch.dto.CustomerSearchResponse;
import com.Nixagh.Learn.common.dto.request.AbsRequest;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import com.Nixagh.Learn.common.process.AbsProcess;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class CustomerSearchProcess extends AbsProcess {
  @Override
  public CustomerSearchResponse process(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {
    CustomerSearchRequest customerSearchRequest = (CustomerSearchRequest) request;
    CustomerSearchResponse customerSearchResponse = (CustomerSearchResponse) response;
    String search = customerSearchRequest.customerSearchConditions.search;
//    StringBuilder str = new StringBuilder();
//    str.append("{$or: {'customerName': ?, 'roomName': ?, 'nationalId': ? }}");
    Query query = new Query();
    query.addCriteria(Criteria.where("customerName").regex(search, "i"));
    System.out.println(query.toString());
    return customerSearchResponse;
  }
  @Override
  public CustomerSearchResponse createNewResponse() {
    return new CustomerSearchResponse();
  }
}
