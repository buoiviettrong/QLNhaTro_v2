package com.Nixagh.Learn.KCP.customer.customerSearch.process;

import com.Nixagh.Learn.KCP.customer.customerSearch.dto.CustomerSearchRequest;
import com.Nixagh.Learn.KCP.customer.customerSearch.dto.CustomerSearchResponse;
import com.Nixagh.Learn.KCP.customer.customerSearch.dto.CustomerSearchRows;
import com.Nixagh.Learn.common.dto.PageInfo;
import com.Nixagh.Learn.common.dto.request.AbsRequest;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import com.Nixagh.Learn.common.process.AbsProcess;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;

public class CustomerSearchProcess extends AbsProcess {
  @Override
  public CustomerSearchResponse process(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {
    CustomerSearchRequest customerSearchRequest = (CustomerSearchRequest) request;
    CustomerSearchResponse customerSearchResponse = (CustomerSearchResponse) response;
    String search = customerSearchRequest.customerSearchConditions.search;
    String id = customerSearchRequest.customerSearchConditions.id;
    String userId = customerSearchRequest.accessInfo.userId;
    PageInfo pageInfo = customerSearchRequest.pageInfo;

    // Create query
    Query query = new Query();
    if (id != null && !id.equals("")) query.addCriteria(Criteria.where("id").is(id));
    else {
      query.addCriteria(new Criteria().orOperator(
              new Criteria("customerName").regex(search, "i"),
              new Criteria("roomName").regex(search, "i"),
              new Criteria("nationalId").regex(search, "i")));
    }
    query.addCriteria(new Criteria("userId").is(userId));

    long totalElements = mongoTemplate.count(query, CustomerSearchRows.class, "Customer");
    // page info
    query.skip((long) (pageInfo.pageNum - 1) * pageInfo.displayNum);
    query.limit(pageInfo.displayNum);
    // Execute query
    customerSearchResponse.rows = (ArrayList<CustomerSearchRows>) mongoTemplate.find(query, CustomerSearchRows.class, "Customer");

    customerSearchResponse.pageInfo.totalElements = totalElements;
    customerSearchResponse.pageInfo.totalPage = totalElements % pageInfo.displayNum == 0
            ? totalElements / pageInfo.displayNum
            : totalElements / pageInfo.displayNum + 1;
    customerSearchResponse.pageInfo.currentPage = pageInfo.pageNum;
    // Return
    return customerSearchResponse;
  }

  @Override
  public CustomerSearchResponse createNewResponse() {
    return new CustomerSearchResponse();
  }
}
