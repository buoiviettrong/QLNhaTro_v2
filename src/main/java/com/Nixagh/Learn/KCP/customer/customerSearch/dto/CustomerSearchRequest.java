package com.Nixagh.Learn.KCP.customer.customerSearch.dto;

import com.Nixagh.Learn.common.dto.request.AbsRequest;

public class CustomerSearchRequest extends AbsRequest {
  public CustomerSearchConditions customerSearchConditions = new CustomerSearchConditions();
}
