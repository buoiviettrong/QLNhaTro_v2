package com.Nixagh.Learn.KCP.room.roomUpdate.dto;

import com.Nixagh.Learn.common.dto.AbsDto;

import java.util.ArrayList;

public class RoomUpdateDto extends AbsDto {
  public String roomName;
  public int status;
  public String id;
  public double price;
  public ArrayList<String> customers;
  public String userId;
}
