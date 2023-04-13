package com.Nixagh.Learn.KCP.customer.customerSearch.dto;

import com.Nixagh.Learn.common.dto.response.AbsResponse;

import java.util.ArrayList;

public class CustomerSearchWithArrayResponse extends AbsResponse {
  public ArrayList<CustomerSearchWithArrayRows> rows = new ArrayList<>();
}

