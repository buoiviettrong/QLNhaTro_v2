package com.Nixagh.Learn.KCP.Authorized.login.process;

import com.Nixagh.Learn.KCP.Authorized.login.dto.LoginInfo;
import com.Nixagh.Learn.KCP.Authorized.login.dto.LoginResponse;
import com.Nixagh.Learn.KCP.Authorized.login.dto.LoginResult;
import com.Nixagh.Learn.common.dto.errorDto;
import com.Nixagh.Learn.common.utilities.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class LoginProcess {

  @Autowired
  MongoTemplate mongoTemplate;

  public LoginResponse login(LoginInfo info) {
    LoginResponse response = new LoginResponse();

    String username = info.username;
    String password = info.password;

    Query query = new Query();
    query.addCriteria(Criteria.where("username").is(username));

    LoginResult result = mongoTemplate.findOne(query, LoginResult.class, "User");

    // check info
    if (result == null) {
      response.addError(new errorDto("LOGIN_FAILED", "UserName: \"" + username + "\" does not exist"));
      return response;
    }
    if (!result.password.equals(password)) {
      response.addError(new errorDto("LOGIN_FAILED", "Password does not match"));
      return response;
    }
    // create token in database
    String token = Token.generateToken();
    // set response
    response.loginResult.username = username;
    response.loginResult.id = result.id;
    response.loginResult.token = token;
    response.loginResult.timestamp = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

    mongoTemplate.save(response.loginResult, "Authentication");

    return response;
  }

  public class R extends LoginResult {
    public String password;
  }
}
