package com.Nixagh.Learn.KCP.priceCalulation.priceCalUpdate.webService;

import com.Nixagh.Learn.KCP.priceCalulation.priceCalUpdate.dto.PriceCalUpdateStatusRequest;
import com.Nixagh.Learn.KCP.priceCalulation.priceCalUpdate.dto.PriceCalUpdateStatusResponse;
import com.Nixagh.Learn.KCP.priceCalulation.priceCalUpdate.process.PriceCalUpdateStatusProcess;
import com.Nixagh.Learn.common.process.AbsProcess;
import com.Nixagh.Learn.common.webService.AbsWebService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceCalUpdateStatusWebService extends AbsWebService {
  @PostMapping("priceCalUpdateStatus")
  public PriceCalUpdateStatusResponse updateStatus(@RequestBody PriceCalUpdateStatusRequest request) {
    return (PriceCalUpdateStatusResponse) super.executeProcess(request);
  }

  @Override
  protected AbsProcess getProcess() {
    return new PriceCalUpdateStatusProcess();
  }
}
