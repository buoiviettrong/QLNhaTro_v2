package com.Nixagh.Learn.KCP.receipt.receiptCreate.dto;

import com.Nixagh.Learn.common.dto.AbsDto;
import com.Nixagh.Learn.common.dto.EWPrice;
import com.Nixagh.Learn.common.dto.ElectricIndex;
import com.Nixagh.Learn.common.dto.WaterIndex;

import java.util.ArrayList;

public class ReceiptCreateDto extends AbsDto {
  public String roomName;
  public double roomPrice;
  public ElectricIndex electricIndex;
  public WaterIndex waterIndex;
  public double deposit;
  public double totalRevenue;
  public double totalMoney;
  public ArrayList<EWPrice> Price;
  public String userId;
  public String timestamp;
  public double remainingMoney;
  public double amountPaid;
}
