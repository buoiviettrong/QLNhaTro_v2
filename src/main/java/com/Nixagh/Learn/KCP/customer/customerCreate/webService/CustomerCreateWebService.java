package com.Nixagh.Learn.KCP.customer.customerCreate.webService;

import com.Nixagh.Learn.KCP.customer.customerCreate.dto.CustomerCreateRequest;
import com.Nixagh.Learn.KCP.customer.customerCreate.dto.CustomerCreateResponse;
import com.Nixagh.Learn.KCP.customer.customerCreate.process.CustomerCreateProcess;
import com.Nixagh.Learn.common.process.AbsProcess;
import com.Nixagh.Learn.common.webService.AbsWebService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerCreateWebService extends AbsWebService {
  @PostMapping("customerCreate")
  public CustomerCreateResponse create(@RequestBody CustomerCreateRequest request) {
    System.out.println(request.customerCreateDto.customerName);
    return (CustomerCreateResponse) super.executeProcess(request);
  }

  @Override
  protected AbsProcess getProcess() {
    return new CustomerCreateProcess();
  }
}
