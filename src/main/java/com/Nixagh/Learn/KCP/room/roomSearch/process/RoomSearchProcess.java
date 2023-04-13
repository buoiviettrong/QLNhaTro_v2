package com.Nixagh.Learn.KCP.room.roomSearch.process;

import com.Nixagh.Learn.KCP.room.roomSearch.dto.RoomSearchRequest;
import com.Nixagh.Learn.KCP.room.roomSearch.dto.RoomSearchResponse;
import com.Nixagh.Learn.KCP.room.roomSearch.dto.RoomSearchRows;
import com.Nixagh.Learn.common.dto.errorDto;
import com.Nixagh.Learn.common.dto.request.AbsRequest;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import com.Nixagh.Learn.common.process.AbsProcess;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;

public class RoomSearchProcess extends AbsProcess {
  @Override
  public RoomSearchResponse process(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {
    RoomSearchRequest roomSearchRequest = (RoomSearchRequest) request;
    RoomSearchResponse roomSearchResponse = (RoomSearchResponse) response;
    long pageNum = roomSearchRequest.pageInfo.pageNum;
    long pageSize = roomSearchRequest.pageInfo.displayNum;

    String id = roomSearchRequest.roomSearchConditions.id;
    ArrayList<RoomSearchRows> lst = new ArrayList<>();
    Query query = new Query();
    if (id != null && !"".equals(id)) query.addCriteria(Criteria.where("id").is(id));
    else {
      String roomName = roomSearchRequest.roomSearchConditions.roomName;
      int status = roomSearchRequest.roomSearchConditions.status;

      if (status != -1) query.addCriteria(Criteria.where("status").is(status));
      if (roomName != null && !"".equals(roomName)) query.addCriteria(Criteria.where("roomName").is(roomName));
    }
    long totalElements = mongoTemplate.count(query, RoomSearchRows.class, "Room");
    query.skip((pageNum - 1) * pageSize);
    query.limit((int) pageSize);
    query.with(Sort.by("roomName").ascending());
    try {
      lst = (ArrayList<RoomSearchRows>) mongoTemplate.find(query, RoomSearchRows.class, "Room");
    } catch (Exception e) {
      roomSearchResponse.addError(new errorDto("ROOM_SEARCH", "Error processing"));
    }
    long totalPage = totalElements % pageSize == 0
            ? totalElements / pageSize
            : totalElements / pageSize + 1;
    roomSearchResponse.pageInfo.totalElements = totalElements;
    roomSearchResponse.pageInfo.currentPage = pageNum;
    roomSearchResponse.pageInfo.totalPage = totalPage;

    roomSearchResponse.rows = lst;
    return roomSearchResponse;
  }

  @Override
  public RoomSearchResponse createNewResponse() {
    return new RoomSearchResponse();
  }
}
