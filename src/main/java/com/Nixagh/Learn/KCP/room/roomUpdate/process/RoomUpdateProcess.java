package com.Nixagh.Learn.KCP.room.roomUpdate.process;

import com.Nixagh.Learn.KCP.room.roomUpdate.dto.RoomUpdateRequest;
import com.Nixagh.Learn.KCP.room.roomUpdate.dto.RoomUpdateResponse;
import com.Nixagh.Learn.KCP.room.roomUpdate.dto.RoomUpdateDto;
import com.Nixagh.Learn.common.dto.request.AbsRequest;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import com.Nixagh.Learn.common.process.AbsProcess;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;

public class RoomUpdateProcess extends AbsProcess {
  @Override
  public RoomUpdateResponse process(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {
    RoomUpdateRequest roomUpdateRequest = (RoomUpdateRequest) request;
    RoomUpdateResponse roomSearchResponse = (RoomUpdateResponse) response;
    
    String id = roomUpdateRequest.roomUpdateDto.id;
    String name = roomUpdateRequest.roomUpdateDto.roomName;
    int status = roomUpdateRequest.roomUpdateDto.status;
    double price = roomUpdateRequest.roomUpdateDto.price;
    ArrayList<String> customers = roomUpdateRequest.roomUpdateDto.customers;
    
    Query query = new Query();
    query.addCriteria(Criteria.where("_id").is(new ObjectId(id)));
    RoomUpdateDto room = mongoTemplate.findOne(query, RoomUpdateDto.class, "Room");
    System.out.println(query.toString());
    if(customers != null && !customers.isEmpty()) {
      room.customers = customers;
      room.status = 1;
    }
    if(status != -1) room.status = status;
    if(price != 0) room.price = price;
    if(name != null && !name.isEmpty()) room.roomName = name;
    mongoTemplate.save(room, "Room");
    return roomSearchResponse;
  }
  @Override
  public RoomUpdateResponse createNewResponse() { return new RoomUpdateResponse(); }
}
