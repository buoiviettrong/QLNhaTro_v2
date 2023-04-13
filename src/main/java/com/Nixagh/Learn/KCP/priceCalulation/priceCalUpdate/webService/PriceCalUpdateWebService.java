package com.Nixagh.Learn.KCP.priceCalulation.priceCalUpdate.webService;

import com.Nixagh.Learn.KCP.priceCalulation.priceCalUpdate.dto.PriceCalUpdateRequest;
import com.Nixagh.Learn.KCP.priceCalulation.priceCalUpdate.dto.PriceCalUpdateResponse;
import com.Nixagh.Learn.KCP.priceCalulation.priceCalUpdate.process.PriceCalUpdateProcess;
import com.Nixagh.Learn.common.process.AbsProcess;
import com.Nixagh.Learn.common.webService.AbsWebService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceCalUpdateWebService extends AbsWebService {
  @PostMapping("priceCalUpdate")
  public PriceCalUpdateResponse create(@RequestBody PriceCalUpdateRequest request) {
    return (PriceCalUpdateResponse) super.executeProcess(request);
  }

  @Override
  protected AbsProcess getProcess() {
    return new PriceCalUpdateProcess();
  }
}
