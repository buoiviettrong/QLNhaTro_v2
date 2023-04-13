package com.Nixagh.Learn.KCP.room.roomCreate.process;

import com.Nixagh.Learn.KCP.room.roomCreate.dto.RoomCreateRequest;
import com.Nixagh.Learn.KCP.room.roomCreate.dto.RoomCreateResponse;
import com.Nixagh.Learn.common.dto.errorDto;
import com.Nixagh.Learn.common.dto.request.AbsRequest;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import com.Nixagh.Learn.common.process.AbsProcess;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class RoomCreateProcess extends AbsProcess {
  @Override
  public void beforeProcess(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {
    RoomCreateRequest roomCreateRequest = (RoomCreateRequest) request;
    RoomCreateResponse roomCreateResponse = (RoomCreateResponse) response;

    String roomName = roomCreateRequest.roomCreateDto.roomName;
    String userId = roomCreateRequest.accessInfo.userId;

    Query query = new Query();
    query.addCriteria(Criteria.where("roomName").is(roomName));
    query.addCriteria(Criteria.where("userId").is(userId));

    try {
      var result = mongoTemplate.find(query, Object.class, "Room");
      if (result.size() > 0) roomCreateResponse.addError(new errorDto("ROOM_CREATE", "Room name is already"));
    } catch (Exception e) {
      roomCreateResponse.addError(new errorDto("ROOM_CREATE", "Error while search in creating room"));
    }
  }

  @Override
  public RoomCreateResponse process(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {
    RoomCreateRequest roomCreateRequest = (RoomCreateRequest) request;
    RoomCreateResponse roomCreateResponse = (RoomCreateResponse) response;

    roomCreateRequest.roomCreateDto.userId = roomCreateRequest.accessInfo.userId;
    try {
      var result = mongoTemplate.save(roomCreateRequest.roomCreateDto, "Room");
      roomCreateResponse.id = result.id;
    } catch (Exception e) {
      roomCreateResponse.addError(new errorDto("ROOM_CREATE", "Error while creating room"));
    }

    return roomCreateResponse;
  }

  @Override
  public RoomCreateResponse createNewResponse() {
    return new RoomCreateResponse();
  }
}
