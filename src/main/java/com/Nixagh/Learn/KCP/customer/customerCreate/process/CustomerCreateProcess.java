package com.Nixagh.Learn.KCP.customer.customerCreate.process;

import com.Nixagh.Learn.KCP.customer.customerCreate.dto.CustomerCreateRequest;
import com.Nixagh.Learn.KCP.customer.customerCreate.dto.CustomerCreateResponse;
import com.Nixagh.Learn.common.dto.errorDto;
import com.Nixagh.Learn.common.dto.request.AbsRequest;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import com.Nixagh.Learn.common.process.AbsProcess;
import org.springframework.data.mongodb.core.MongoTemplate;

public class CustomerCreateProcess extends AbsProcess {
  @Override
  public CustomerCreateResponse beforeProcess(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {

    return response;
  }

  @Override
  public CustomerCreateResponse process(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {
    CustomerCreateRequest customerCreateRequest = (CustomerCreateRequest) request;
    CustomerCreateResponse customerCreateResponse = (CustomerCreateResponse) response;


    customerCreateRequest.customerCreateDto.userId = customerCreateRequest.accessInfo.userId;
    try {
      mongoTemplate.save(customerCreateRequest.customerCreateDto, "Customer");
    } catch (Exception e) {
      customerCreateResponse.addError(new errorDto("CUSTOMER_CREATE", "Customer create failed");
    }
    return customerCreateResponse;
  }

  @Override
  public CustomerCreateResponse createNewResponse() {
    return new CustomerCreateResponse();
  }
}
