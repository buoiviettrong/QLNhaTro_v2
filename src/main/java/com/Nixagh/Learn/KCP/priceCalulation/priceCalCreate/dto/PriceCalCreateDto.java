package com.Nixagh.Learn.KCP.priceCalulation.priceCalCreate.dto;

import com.Nixagh.Learn.common.dto.AbsDto;
import com.Nixagh.Learn.common.dto.ElectricIndex;
import com.Nixagh.Learn.common.dto.WaterIndex;

public class PriceCalCreateDto extends AbsDto {
  public String roomName;
  public String roomId;
  public double roomPrice;
  public String userId;
  public ElectricIndex electricIndex = new ElectricIndex();
  public WaterIndex waterIndex = new WaterIndex();
  public double deposit;
  public double totalRevenue;
  public String timestamp;
  public int Status = 0;
}
