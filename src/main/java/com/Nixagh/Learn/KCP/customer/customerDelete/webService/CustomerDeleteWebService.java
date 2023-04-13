package com.Nixagh.Learn.KCP.customer.customerDelete.webService;

import com.Nixagh.Learn.KCP.customer.customerDelete.dto.CustomerDeleteRequest;
import com.Nixagh.Learn.KCP.customer.customerDelete.dto.CustomerDeleteResponse;
import com.Nixagh.Learn.KCP.customer.customerDelete.process.CustomerDeleteProcess;
import com.Nixagh.Learn.common.process.AbsProcess;
import com.Nixagh.Learn.common.webService.AbsWebService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerDeleteWebService extends AbsWebService {
  @PostMapping("customerDelete")
  public CustomerDeleteResponse delete(@RequestBody CustomerDeleteRequest request) {
    return (CustomerDeleteResponse) super.executeProcess(request);
  }

  @Override
  protected AbsProcess getProcess() {
    return new CustomerDeleteProcess();
  }
}
