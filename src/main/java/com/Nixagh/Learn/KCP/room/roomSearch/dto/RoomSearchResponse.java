package com.Nixagh.Learn.KCP.room.roomSearch.dto;

import com.Nixagh.Learn.common.dto.PageInfoResponse;
import com.Nixagh.Learn.common.dto.response.AbsResponse;

import java.util.ArrayList;

public class RoomSearchResponse extends AbsResponse {
  public ArrayList<RoomSearchRows> rows;
  public PageInfoResponse pageInfo = new PageInfoResponse();
}
