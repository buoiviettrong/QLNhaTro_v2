package com.Nixagh.Learn.KCP.priceCalulation.priceCalSearch.dto;

import com.Nixagh.Learn.common.dto.AbsDto;
import com.Nixagh.Learn.common.dto.EWPrice;
import com.Nixagh.Learn.common.dto.ElectricIndex;
import com.Nixagh.Learn.common.dto.WaterIndex;

import java.util.ArrayList;

public class PriceCalSearchRow extends AbsDto {
  public String id;
  public String roomName;
  public double roomPrice;
  public int Status;
  public ElectricIndex electricIndex;
  public WaterIndex waterIndex;
  public double deposit;
  public double totalRevenue;
  public double totalMoney;
  public ArrayList<EWPrice> Price;
  public double totalMoneyOfElectric;
  public double totalMoneyOfWater;
}
