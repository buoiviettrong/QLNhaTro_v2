package com.Nixagh.Learn.KCP.customer.customerSearch.dto;

import com.Nixagh.Learn.common.dto.PageInfoResponse;
import com.Nixagh.Learn.common.dto.response.AbsResponse;

import java.util.ArrayList;

public class CustomerSearchResponse extends AbsResponse {
  public ArrayList<CustomerSearchRows> rows = new ArrayList<CustomerSearchRows>();
  public PageInfoResponse pageInfo = new PageInfoResponse();
}
