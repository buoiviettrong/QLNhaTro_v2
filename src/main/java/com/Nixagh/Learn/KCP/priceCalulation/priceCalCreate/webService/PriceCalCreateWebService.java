package com.Nixagh.Learn.KCP.priceCalulation.priceCalCreate.webService;

import com.Nixagh.Learn.KCP.priceCalulation.priceCalCreate.dto.PriceCalCreateRequest;
import com.Nixagh.Learn.KCP.priceCalulation.priceCalCreate.dto.PriceCalCreateResponse;
import com.Nixagh.Learn.KCP.priceCalulation.priceCalCreate.process.PriceCalCreateProcess;
import com.Nixagh.Learn.common.process.AbsProcess;
import com.Nixagh.Learn.common.webService.AbsWebService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceCalCreateWebService extends AbsWebService {
  @PostMapping("priceCalCreate")
  public PriceCalCreateResponse create(@RequestBody PriceCalCreateRequest request) {
    return (PriceCalCreateResponse) super.executeProcess(request);
  }

  @Override
  protected AbsProcess getProcess() {
    return new PriceCalCreateProcess();
  }
}
