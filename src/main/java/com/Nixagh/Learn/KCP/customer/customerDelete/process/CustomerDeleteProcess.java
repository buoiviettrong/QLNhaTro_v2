package com.Nixagh.Learn.KCP.customer.customerDelete.process;

import com.Nixagh.Learn.KCP.customer.customerDelete.dto.CustomerDeleteRequest;
import com.Nixagh.Learn.KCP.customer.customerDelete.dto.CustomerDeleteResponse;
import com.Nixagh.Learn.common.dto.errorDto;
import com.Nixagh.Learn.common.dto.request.AbsRequest;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import com.Nixagh.Learn.common.process.AbsProcess;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class CustomerDeleteProcess extends AbsProcess {
  @Override
  public void beforeProcess(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {
    CustomerDeleteResponse customerDeleteResponse = (CustomerDeleteResponse) response;
    CustomerDeleteRequest customerDeleteRequest = (CustomerDeleteRequest) request;

    String id = customerDeleteRequest.customerDeleteId;
    String userId = customerDeleteRequest.accessInfo.userId;

    Query query = new Query();
    query.addCriteria(Criteria.where("id").is(id));
    query.addCriteria(Criteria.where("userId").is(userId));

    var result = mongoTemplate.find(query, Object.class, "Customer");
    if (result == null)
      customerDeleteResponse.addError(new errorDto("CUSTOMER_DELETE", "Customer Id " + id + " not found"));
  }

  @Override
  public CustomerDeleteResponse process(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {
    CustomerDeleteResponse customerDeleteResponse = (CustomerDeleteResponse) response;
    CustomerDeleteRequest customerDeleteRequest = (CustomerDeleteRequest) request;

    String id = customerDeleteRequest.customerDeleteId;

    Query query = new Query();
    query.addCriteria(Criteria.where("_id").is(new ObjectId(id)));
    try {
      var result = mongoTemplate.remove(query, "Customer");
    } catch (Exception e) {
      customerDeleteResponse.addError(new errorDto("CUSTOMER_DELETE", "ERROR OUTSIDE SYSTEM"));
    }

    return customerDeleteResponse;
  }

  @Override
  public CustomerDeleteResponse createNewResponse() {
    return new CustomerDeleteResponse();
  }
}
