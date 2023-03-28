package com.Nixagh.Learn.KCP.userUpdate.webService;

import com.Nixagh.Learn.KCP.userSearch.dto.UserSearchResponse;
import com.Nixagh.Learn.KCP.userUpdate.dto.UserUpdateRequest;
import com.Nixagh.Learn.KCP.userUpdate.process.userUpdateProcess;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import com.Nixagh.Learn.common.process.AbsProcess;
import com.Nixagh.Learn.common.webService.AbsWebService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userUpdateService extends AbsWebService {

  @PostMapping("userUpdate")
  public UserSearchResponse update(UserUpdateRequest request) {
    AbsResponse abs = super.executeProcess(request);
    return (UserSearchResponse) abs;
  }

  @Override
  protected AbsProcess getProcess() {
    return new userUpdateProcess();
  }
}
