package com.Nixagh.Learn.KCP.room.roomCheckout.process;

import com.Nixagh.Learn.KCP.room.roomCheckout.dto.RoomCheckoutRequest;
import com.Nixagh.Learn.KCP.room.roomCheckout.dto.RoomCheckoutResponse;
import com.Nixagh.Learn.common.dto.errorDto;
import com.Nixagh.Learn.common.dto.request.AbsRequest;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import com.Nixagh.Learn.common.process.AbsProcess;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;

public class RoomCheckoutProcess extends AbsProcess {
  @Override
  public RoomCheckoutResponse process(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {
    RoomCheckoutRequest roomCreateRequest = (RoomCheckoutRequest) request;
    RoomCheckoutResponse roomCreateResponse = (RoomCheckoutResponse) response;

    String id = roomCreateRequest.id;

    Query query = new Query();
    query.addCriteria(Criteria.where("_id").is(new ObjectId(id)));

    Update update = new Update();
    update.set("status", 0);
    update.set("customers", new ArrayList<String>());
    try {
      mongoTemplate.updateFirst(query, update, "Room");
    } catch (Exception e) {
      roomCreateResponse.addError(new errorDto("ROOM_CREATE", "Error while creating room"));
    }

    return roomCreateResponse;
  }

  @Override
  public RoomCheckoutResponse createNewResponse() {
    return new RoomCheckoutResponse();
  }
}
