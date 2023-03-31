package com.Nixagh.Learn.KCP.customer.customerUpdate.dto;

import com.Nixagh.Learn.common.dto.AbsDto;

public class CustomerUpdateDto extends AbsDto {
  public String id;
  public String customerName;
  public int gender;
  public String birthDate;
  public String address;
  public String nationalId;
  public String phoneNumber;
  public String emailAddress;
  public String roomName = "";
  public String roomId;
  public int statusChangeNationalId = 0;
}
