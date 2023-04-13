package com.Nixagh.Learn.KCP.receipt.receiptSearch.dto;

import com.Nixagh.Learn.common.dto.AbsDto;
import com.Nixagh.Learn.common.dto.DateDto;
import com.Nixagh.Learn.common.dto.PriceDto;

public class ReceiptSearchConditions extends AbsDto {
  public String id;
  public PriceDto price;
  public DateDto date;
  public String search;
  public int receiptStatus;
}
