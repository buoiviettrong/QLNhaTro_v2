package com.Nixagh.Learn.KCP.receipt.receiptSearch.dto;

import com.Nixagh.Learn.common.dto.PageInfoResponse;
import com.Nixagh.Learn.common.dto.response.AbsResponse;

import java.util.ArrayList;

public class ReceiptSearchResponse extends AbsResponse {
  public ArrayList<ReceiptSearchRows> rows = new ArrayList<ReceiptSearchRows>();
  public PageInfoResponse pageInfo = new PageInfoResponse();
}
