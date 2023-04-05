package com.Nixagh.Learn.KCP.room.roomSearch.process;

import com.Nixagh.Learn.KCP.room.roomSearch.dto.RoomSearchRequest;
import com.Nixagh.Learn.KCP.room.roomSearch.dto.RoomSearchResponse;
import com.Nixagh.Learn.KCP.room.roomSearch.dto.RoomSearchRows;
import com.Nixagh.Learn.common.dto.errorDto;
import com.Nixagh.Learn.common.dto.request.AbsRequest;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import com.Nixagh.Learn.common.process.AbsProcess;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;

public class RoomSearchProcess extends AbsProcess {
  @Override
  public RoomSearchResponse process(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {
    RoomSearchRequest roomSearchRequest = (RoomSearchRequest) request;
    RoomSearchResponse roomSearchResponse = (RoomSearchResponse) response;

    String id = roomSearchRequest.roomSearchConditions.id;
    ArrayList<RoomSearchRows> lst = new ArrayList<>();
    Query query = new Query();
    if(id != null && !"".equals(id)) query.addCriteria(Criteria.where("id").is(id));
    else {
      String roomName = roomSearchRequest.roomSearchConditions.roomName;
      int status = roomSearchRequest.roomSearchConditions.status;

      if(status != -1) query.addCriteria(Criteria.where("status").is(status));
      if(roomName != null && !"".equals(roomName)) query.addCriteria(Criteria.where("roomName").is(roomName));
    }
    try {
      lst = (ArrayList<RoomSearchRows>) mongoTemplate.find(query, RoomSearchRows.class, "Room");
    } catch (Exception e) {
      roomSearchResponse.addError(new errorDto("ROOM_SEARCH", "Error processing"));
    }
    roomSearchResponse.rows = lst;
    return roomSearchResponse;
  }
  @Override
  public RoomSearchResponse createNewResponse() { return new RoomSearchResponse(); }
}
