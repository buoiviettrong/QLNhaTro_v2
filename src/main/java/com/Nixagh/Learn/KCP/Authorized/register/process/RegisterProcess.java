package com.Nixagh.Learn.KCP.Authorized.register.process;

import com.Nixagh.Learn.KCP.Authorized.register.dto.RegisterRequest;
import com.Nixagh.Learn.KCP.Authorized.register.dto.RegisterResponse;
import com.Nixagh.Learn.common.dto.errorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class RegisterProcess {
  @Autowired
  MongoTemplate mongoTemplate;

  public RegisterResponse before(RegisterRequest request) {
    RegisterResponse response = new RegisterResponse();
    //check exist username
    Query query = new Query();
    query.addCriteria(Criteria.where("username").is(request.registerDto.username));
    var result = mongoTemplate.find(query, Object.class, "User");
    if (result.size() > 0) {
      response.addError(new errorDto("REGISTER", "User already exists"));
      return response;
    }
    //check exist email
    query = new Query();
    query.addCriteria(Criteria.where("email").is(request.registerDto.email));
    result = mongoTemplate.find(query, Object.class, "User");
    if (result.size() > 0) {
      response.addError(new errorDto("REGISTER", "User already exists"));
      return response;
    }
    //check exist phone
    query = new Query();
    query.addCriteria(Criteria.where("phone").is(request.registerDto.phone));
    result = mongoTemplate.find(query, Object.class, "User");
    if (result.size() > 0) {
      response.addError(new errorDto("REGISTER", "User already exists"));
      return response;
    }
    return response;
  }

  public RegisterResponse register(RegisterRequest request) {

    RegisterResponse response = before(request);
    if (response.getErrorList().size() > 0) return response;
    try {
      mongoTemplate.save(request.registerDto, "User");
    } catch (Exception e) {
      response.addError(new errorDto("SYSTEM", "ERROR OUTSIDE SYSTEM"));
    }
    return response;
  }
}
