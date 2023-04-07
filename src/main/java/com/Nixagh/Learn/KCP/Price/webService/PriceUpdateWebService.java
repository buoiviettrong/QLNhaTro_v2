package com.Nixagh.Learn.KCP.Price.webService;

import com.Nixagh.Learn.KCP.Price.dto.PriceUpdateRequest;
import com.Nixagh.Learn.KCP.Price.dto.PriceUpdateResponse;
import com.Nixagh.Learn.KCP.Price.process.PriceUpdateProcess;
import com.Nixagh.Learn.common.process.AbsProcess;
import com.Nixagh.Learn.common.webService.AbsWebService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceUpdateWebService extends AbsWebService {
  @PostMapping("priceUpdate")
  public PriceUpdateResponse update(@RequestBody PriceUpdateRequest request) { return (PriceUpdateResponse) super.executeProcess(request); }
  @Override
  protected AbsProcess getProcess() {
    return new PriceUpdateProcess();
  }
}
