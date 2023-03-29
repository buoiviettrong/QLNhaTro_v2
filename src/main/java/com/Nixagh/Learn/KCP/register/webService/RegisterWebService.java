package com.Nixagh.Learn.KCP.register.webService;

import com.Nixagh.Learn.KCP.register.dto.RegisterDto;
import com.Nixagh.Learn.KCP.register.dto.RegisterRequest;
import com.Nixagh.Learn.KCP.register.dto.RegisterResponse;
import com.Nixagh.Learn.KCP.register.process.RegisterProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterWebService {
  @Autowired
  RegisterProcess registerProcess;
  @PostMapping("register")
  public RegisterResponse register(@RequestBody RegisterRequest request) {
    return registerProcess.register(request);
  }
}
