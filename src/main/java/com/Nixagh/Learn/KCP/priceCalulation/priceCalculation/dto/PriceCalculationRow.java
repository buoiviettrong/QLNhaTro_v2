package com.Nixagh.Learn.KCP.priceCalulation.priceCalculation.dto;

import com.Nixagh.Learn.common.dto.AbsDto;
import com.Nixagh.Learn.common.dto.EWPrice;
import com.Nixagh.Learn.common.dto.ElectricIndex;
import com.Nixagh.Learn.common.dto.WaterIndex;

import java.util.ArrayList;

public class PriceCalculationRow extends AbsDto {
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

  public PriceCalculationRow() {
  }

  public PriceCalculationRow(PriceCalculationRow row) {
    this.id = row.id;
    this.roomName = row.roomName;
    this.roomPrice = row.roomPrice;
    this.Price = row.Price;
    this.Status = row.Status;
    this.electricIndex = row.electricIndex;
    this.waterIndex = row.waterIndex;
    this.deposit = row.deposit;
    this.totalMoney = row.totalMoneyOfElectric + row.totalMoneyOfWater + row.roomPrice;
    this.totalRevenue = (row.totalMoney - row.deposit) < 0 ? 0 : (row.totalMoney - row.deposit);
    this.totalMoneyOfElectric = (row.electricIndex.New - row.electricIndex.Old) * row.Price.get(0).priceOfElectric;
    this.totalMoneyOfWater = (row.waterIndex.New - row.waterIndex.Old) * row.Price.get(0).priceOfWater;
  }

}
