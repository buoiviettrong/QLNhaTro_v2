package com.Nixagh.Learn.KCP.customer.customerSearch.webService;

import com.Nixagh.Learn.KCP.customer.customerSearch.dto.CustomerSearchRequest;
import com.Nixagh.Learn.KCP.customer.customerSearch.dto.CustomerSearchResponse;
import com.Nixagh.Learn.KCP.customer.customerSearch.process.CustomerSearchProcess;
import com.Nixagh.Learn.common.process.AbsProcess;
import com.Nixagh.Learn.common.webService.AbsWebService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerSearchWebService extends AbsWebService {

  @PostMapping("customerSearch")
  public CustomerSearchResponse search(@RequestBody CustomerSearchRequest request) {
    return (CustomerSearchResponse) super.executeProcess(request);
  }

  @Override
  protected AbsProcess getProcess() {
    return new CustomerSearchProcess();
  }
}
