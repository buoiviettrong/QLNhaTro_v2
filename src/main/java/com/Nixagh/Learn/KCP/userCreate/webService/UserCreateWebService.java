package com.Nixagh.Learn.KCP.userCreate.webService;

import com.Nixagh.Learn.KCP.userCreate.dto.UserCreateRequest;
import com.Nixagh.Learn.KCP.userCreate.dto.UserCreateResponse;
import com.Nixagh.Learn.KCP.userCreate.process.UserCreateProcess;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import com.Nixagh.Learn.common.process.AbsProcess;
import com.Nixagh.Learn.common.webService.AbsWebService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserCreateWebService extends AbsWebService {
  @PostMapping("userCreate")
  public UserCreateResponse create(@RequestBody UserCreateRequest request) {
    System.out.println(request.userCreateDto.firstName);
    AbsResponse abs = super.executeProcess(request);
    return (UserCreateResponse) abs;
  }
  @Override
  protected AbsProcess getProcess() {
    return new UserCreateProcess();
  }

}
