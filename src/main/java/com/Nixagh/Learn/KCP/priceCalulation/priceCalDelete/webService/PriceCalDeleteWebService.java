package com.Nixagh.Learn.KCP.priceCalulation.priceCalDelete.webService;

import com.Nixagh.Learn.KCP.priceCalulation.priceCalDelete.dto.PriceCalDeleteRequest;
import com.Nixagh.Learn.KCP.priceCalulation.priceCalDelete.dto.PriceCalDeleteResponse;
import com.Nixagh.Learn.KCP.priceCalulation.priceCalDelete.process.PriceCalDeleteProcess;
import com.Nixagh.Learn.common.process.AbsProcess;
import com.Nixagh.Learn.common.webService.AbsWebService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceCalDeleteWebService extends AbsWebService {
  @PostMapping("priceCalDelete")
  public PriceCalDeleteResponse create(@RequestBody PriceCalDeleteRequest request) {
    return (PriceCalDeleteResponse) super.executeProcess(request);
  }

  @Override
  protected AbsProcess getProcess() {
    return new PriceCalDeleteProcess();
  }
}
