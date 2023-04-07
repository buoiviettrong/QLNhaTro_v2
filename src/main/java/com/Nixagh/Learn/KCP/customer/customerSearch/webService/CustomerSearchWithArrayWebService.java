package com.Nixagh.Learn.KCP.customer.customerSearch.webService;

import com.Nixagh.Learn.KCP.customer.customerSearch.dto.CustomerSearchWithArrayRequest;
import com.Nixagh.Learn.KCP.customer.customerSearch.dto.CustomerSearchWithArrayResponse;
import com.Nixagh.Learn.KCP.customer.customerSearch.process.CustomerSearchWithArrayProcess;
import com.Nixagh.Learn.common.process.AbsProcess;
import com.Nixagh.Learn.common.webService.AbsWebService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerSearchWithArrayWebService extends AbsWebService {
  @PostMapping("customerSearchWithArray")
  public CustomerSearchWithArrayResponse search(@RequestBody CustomerSearchWithArrayRequest request) {
    return (CustomerSearchWithArrayResponse) super.executeProcess(request);
  }

  @Override
  protected AbsProcess getProcess() {
    return new CustomerSearchWithArrayProcess();
  }
}
