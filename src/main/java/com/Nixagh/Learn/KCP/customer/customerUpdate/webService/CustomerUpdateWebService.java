package com.Nixagh.Learn.KCP.customer.customerUpdate.webService;

import com.Nixagh.Learn.KCP.customer.customerUpdate.dto.CustomerUpdateRequest;
import com.Nixagh.Learn.KCP.customer.customerUpdate.dto.CustomerUpdateResponse;
import com.Nixagh.Learn.KCP.customer.customerUpdate.process.CustomerUpdateProcess;
import com.Nixagh.Learn.common.process.AbsProcess;
import com.Nixagh.Learn.common.webService.AbsWebService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerUpdateWebService extends AbsWebService {
  @PostMapping("customerUpdate")
  public CustomerUpdateResponse update(@RequestBody CustomerUpdateRequest request) {
    return (CustomerUpdateResponse) super.executeProcess(request);
  }

  @Override
  protected AbsProcess getProcess() {
    return new CustomerUpdateProcess();
  }
}
