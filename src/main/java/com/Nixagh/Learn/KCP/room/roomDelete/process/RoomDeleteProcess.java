package com.Nixagh.Learn.KCP.room.roomDelete.process;

import com.Nixagh.Learn.KCP.room.roomDelete.dto.RoomDeleteRequest;
import com.Nixagh.Learn.KCP.room.roomDelete.dto.RoomDeleteResponse;
import com.Nixagh.Learn.common.dto.errorDto;
import com.Nixagh.Learn.common.dto.request.AbsRequest;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import com.Nixagh.Learn.common.process.AbsProcess;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class RoomDeleteProcess extends AbsProcess {
  @Override
  public RoomDeleteResponse process(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {
    RoomDeleteResponse roomDeleteResponse = (RoomDeleteResponse) response;
    RoomDeleteRequest roomDeleteRequest = (RoomDeleteRequest) request;

    String id = roomDeleteRequest.id;
    try {
      mongoTemplate.remove(new Query().addCriteria(Criteria.where("_id").is(new ObjectId(id))), "Room");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      roomDeleteResponse.addError(new errorDto("ROOM_DELETE", "Error processing request to delete"));
    }

    return roomDeleteResponse;
  }

  public RoomDeleteResponse createNewResponse() {
    return new RoomDeleteResponse();
  }
}
