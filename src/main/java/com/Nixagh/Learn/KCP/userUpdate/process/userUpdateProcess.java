package com.Nixagh.Learn.KCP.userUpdate.process;

import com.Nixagh.Learn.KCP.userUpdate.dto.UserUpdateRequest;
import com.Nixagh.Learn.KCP.userUpdate.dto.UserUpdateResponse;
import com.Nixagh.Learn.common.dto.request.AbsRequest;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import com.Nixagh.Learn.common.process.AbsProcess;
import org.springframework.data.mongodb.core.MongoTemplate;

public class userUpdateProcess extends AbsProcess {
  @Override
  public UserUpdateResponse process(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {
    UserUpdateRequest userUpdateRequest = (UserUpdateRequest) request;
    UserUpdateResponse userUpdateResponse = (UserUpdateResponse) response;

    return userUpdateResponse;
  }

  public UserUpdateResponse createNewResponse(AbsRequest request) {
    return new UserUpdateResponse();
  }
}
