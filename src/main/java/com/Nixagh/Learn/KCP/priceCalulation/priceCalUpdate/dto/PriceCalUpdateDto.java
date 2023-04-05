package com.Nixagh.Learn.KCP.priceCalulation.priceCalUpdate.dto;

import com.Nixagh.Learn.common.dto.AbsDto;
import com.Nixagh.Learn.common.dto.ElectricIndex;
import com.Nixagh.Learn.common.dto.WaterIndex;

public class PriceCalUpdateDto extends AbsDto {
  public String id;
  public int Status;
  public ElectricIndex electricIndex;
  public WaterIndex waterIndex;
  public double deposit;
}
