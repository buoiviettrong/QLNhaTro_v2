package com.Nixagh.Learn.KCP.login.webService;

import com.Nixagh.Learn.KCP.login.dto.LoginInfo;
import com.Nixagh.Learn.KCP.login.dto.LoginRequest;
import com.Nixagh.Learn.KCP.login.dto.LoginResponse;
import com.Nixagh.Learn.KCP.login.process.LoginProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginWebService{
  @Autowired
  LoginProcess loginProcess;
  @PostMapping("Login")
  public LoginResponse login(@RequestBody LoginRequest request) {
    return loginProcess.login(request.loginInfo);
  }
}
