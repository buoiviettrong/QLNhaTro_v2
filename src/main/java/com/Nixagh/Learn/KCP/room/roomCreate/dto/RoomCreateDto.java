package com.Nixagh.Learn.KCP.room.roomCreate.dto;

import com.Nixagh.Learn.common.dto.AbsDto;

import java.util.ArrayList;

public class RoomCreateDto extends AbsDto {
  public String id;
  public String roomName;
  public int status = 0;
  public int price = 0;
  public String userId;
  public ArrayList<String> customers = new ArrayList<String>();
}
