package com.Nixagh.Learn.KCP.Authorized.AuthorizedWebService;

import com.Nixagh.Learn.KCP.Authorized.AuthorizedProcess.AuthorizedProcess;
import com.Nixagh.Learn.common.dto.request.AbsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizedWebService {
  @Autowired
  AuthorizedProcess authorizedProcess;

  @PostMapping("Authorized")
  public boolean authorize(@RequestBody AbsRequest request) {
    return authorizedProcess.authorize(request);
  }
}
