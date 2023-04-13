package com.Nixagh.Learn.KCP.Authorized.logout.webService;

import com.Nixagh.Learn.KCP.Authorized.logout.dto.LogOutRequest;
import com.Nixagh.Learn.KCP.Authorized.logout.dto.LogOutResponse;
import com.Nixagh.Learn.KCP.Authorized.logout.process.LogOutProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogOutWebService {
  @Autowired
  LogOutProcess logoutProcess;

  @PostMapping("logout")
  public LogOutResponse logout(@RequestBody LogOutRequest request) {
    return logoutProcess.logout(request);
  }
}
