package com.Nixagh.Learn.KCP.room.roomSearch.dto;

import com.Nixagh.Learn.common.dto.AbsDto;

import java.util.ArrayList;

public class RoomSearchRows extends AbsDto {
  public String id;
  public String roomName;
  public int status;
  public String price;
  public ArrayList<String> customers;
}
