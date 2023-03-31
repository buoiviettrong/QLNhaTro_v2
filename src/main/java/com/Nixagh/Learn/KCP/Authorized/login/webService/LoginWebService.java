package com.Nixagh.Learn.KCP.Authorized.login.webService;

import com.Nixagh.Learn.KCP.Authorized.login.dto.LoginRequest;
import com.Nixagh.Learn.KCP.Authorized.login.dto.LoginResponse;
import com.Nixagh.Learn.KCP.Authorized.login.process.LoginProcess;
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
