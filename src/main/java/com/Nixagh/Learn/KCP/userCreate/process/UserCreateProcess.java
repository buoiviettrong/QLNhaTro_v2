package com.Nixagh.Learn.KCP.userCreate.process;

import com.Nixagh.Learn.KCP.user.userCreate.UserCreateResult;
import com.Nixagh.Learn.KCP.userCreate.dto.UserCreateRequest;
import com.Nixagh.Learn.KCP.userCreate.dto.UserCreateResponse;
import com.Nixagh.Learn.common.dto.errorDto;
import com.Nixagh.Learn.common.dto.request.AbsRequest;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import com.Nixagh.Learn.common.process.AbsProcess;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserCreateProcess extends AbsProcess {

  @Override
  protected UserCreateResponse process(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {
    UserCreateRequest userCreateRequest = (UserCreateRequest) request;
    UserCreateResponse userCreateResponse = (UserCreateResponse) response;

    // get Parameters
    String username = userCreateRequest.userCreateDto.username;
    String password = userCreateRequest.userCreateDto.password;
    String email = userCreateRequest.userCreateDto.email;
    String firstName = userCreateRequest.userCreateDto.firstName;
    String lastName = userCreateRequest.userCreateDto.lastName;
    String phoneNumber = userCreateRequest.userCreateDto.phoneNumber;
    String address = userCreateRequest.userCreateDto.address;
    String gender = userCreateRequest.userCreateDto.gender;
    String birthday = userCreateRequest.userCreateDto.birthday;
    String description = userCreateRequest.userCreateDto.description;

    UserCreateResult user = new UserCreateResult();
    user.username = username;
    user.password = password;
    user.email = email;
    user.firstName = firstName;
    user.lastName = lastName;
    user.phoneNumber = phoneNumber;
    user.address = address;
    user.gender = gender;
    user.birthday = birthday;
    user.description = description;
    try {
      var result = mongoTemplate.insert(user, "User");

      userCreateResponse.rows = result;

    } catch (Exception e) {
      System.out.println(e.getMessage());
      userCreateResponse.addError(new errorDto("USER_CREATE", "Could not insert user into database"));
    }
    return userCreateResponse;
  }

  @Override
  protected UserCreateResponse createNewResponse(AbsRequest request) {
    return new UserCreateResponse();
  }
}
