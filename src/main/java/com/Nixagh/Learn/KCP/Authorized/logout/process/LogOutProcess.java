package com.Nixagh.Learn.KCP.Authorized.logout.process;

import com.Nixagh.Learn.KCP.Authorized.logout.dto.LogOutRequest;
import com.Nixagh.Learn.KCP.Authorized.logout.dto.LogOutResponse;
import com.Nixagh.Learn.common.dto.errorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class LogOutProcess {
  @Autowired
  MongoTemplate mongoTemplate;

  public LogOutResponse logout(LogOutRequest request) {
    String id = request.accessInfo.userId;
    String token = request.accessInfo.token;

    LogOutResponse response = new LogOutResponse();

    Query query = new Query();
    query.addCriteria(Criteria.where("id").is(id));
    query.addCriteria(Criteria.where("token").is(token));
    try {
      var result = mongoTemplate.remove(query, "Authentication");
    } catch (Exception e) {
      response.addError(new errorDto("LOGOUT", "LogOut failed"));
    }
    return response;
  }
}
