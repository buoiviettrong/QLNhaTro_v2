package com.Nixagh.Learn.KCP.customer.customerUpdate.process;

import com.Nixagh.Learn.KCP.customer.customerUpdate.dto.CustomerUpdateRequest;
import com.Nixagh.Learn.KCP.customer.customerUpdate.dto.CustomerUpdateResponse;
import com.Nixagh.Learn.KCP.customer.customerUpdate.dto.CustomerUpdateSearch;
import com.Nixagh.Learn.common.dto.errorDto;
import com.Nixagh.Learn.common.dto.request.AbsRequest;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import com.Nixagh.Learn.common.process.AbsProcess;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class CustomerUpdateProcess extends AbsProcess {
  @Override
  public void beforeProcess(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {
    CustomerUpdateRequest customerUpdateRequest = (CustomerUpdateRequest) request;
    CustomerUpdateResponse customerUpdateResponse = (CustomerUpdateResponse) response;

    if (customerUpdateRequest.customerUpdateDto.statusChangeNationalId == 0) return;

    String nationalId = customerUpdateRequest.customerUpdateDto.nationalId;
    String userId = customerUpdateRequest.accessInfo.userId;
    Query query = new Query();
    query.addCriteria(Criteria.where("nationalId").is(nationalId));
    query.addCriteria(Criteria.where("userId").is(userId));

    var result = mongoTemplate.find(query, Object.class, "Customer");
    if (!result.isEmpty())
      customerUpdateResponse.addError(new errorDto("CUSTOMER_UPDATE", "National ID " + nationalId + " is already"));
  }

  @Override
  public CustomerUpdateResponse process(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {
    CustomerUpdateRequest customerUpdateRequest = (CustomerUpdateRequest) request;
    CustomerUpdateResponse customerUpdateResponse = (CustomerUpdateResponse) response;

    String id = customerUpdateRequest.customerUpdateDto.id;
    String customerName = customerUpdateRequest.customerUpdateDto.customerName;
    String address = customerUpdateRequest.customerUpdateDto.address;
    int gender = customerUpdateRequest.customerUpdateDto.gender;
    String birthDate = customerUpdateRequest.customerUpdateDto.birthDate;
    String phoneNumber = customerUpdateRequest.customerUpdateDto.phoneNumber;
    String emailAddress = customerUpdateRequest.customerUpdateDto.emailAddress;
    String userId = customerUpdateRequest.accessInfo.userId;
    String nationalId = customerUpdateRequest.customerUpdateDto.nationalId;

    try {
      Query query = new Query();
      query.addCriteria(Criteria.where("id").is(new ObjectId(id)));
      query.addCriteria(Criteria.where("userId").is(userId));
      System.out.println("CUSTOMER UPDATE::::" + query);
      CustomerUpdateSearch result = mongoTemplate.findOne(query, CustomerUpdateSearch.class, "Customer");

      if (result == null) {
        customerUpdateResponse.addError(new errorDto("CUSTOMER_UPDATE", "Customer ID " + id + " not found"));
        return customerUpdateResponse;
      }

      result.customerName = customerName;
      result.address = address;
      result.gender = gender;
      result.birthDate = birthDate;
      result.phoneNumber = phoneNumber;
      result.emailAddress = emailAddress;
      result.nationalId = nationalId;

      var r = mongoTemplate.save(result, "Customer");
      System.out.println(r.id);
    } catch (Exception e) {
      customerUpdateResponse.addError(new errorDto("CUSTOMER_UPDATE", "ERROR OUTSIDE SYSTEM"));
    }
    return customerUpdateResponse;
  }

  @Override
  public CustomerUpdateResponse createNewResponse() {
    return new CustomerUpdateResponse();
  }
}
