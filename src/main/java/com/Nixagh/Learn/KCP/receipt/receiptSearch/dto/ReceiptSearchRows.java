package com.Nixagh.Learn.KCP.receipt.receiptSearch.dto;

import com.Nixagh.Learn.common.dto.AbsDto;

import java.util.ArrayList;

public class ReceiptSearchRows extends AbsDto {
  public String id;
  public String name;
  public int w_old;
  public int w_new;
  public int w_total;
  public int e_old;
  public int e_new;
  public int e_total;

  public int deposit;
  public int total;
  public int r_price;
  public String timestamp;
  public int w_price;
  public int e_price;
}
