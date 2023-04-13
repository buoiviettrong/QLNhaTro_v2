package com.Nixagh.Learn.KCP.priceCalulation.priceCalculation.webService;

import com.Nixagh.Learn.KCP.priceCalulation.priceCalculation.dto.PriceCalculationRequest;
import com.Nixagh.Learn.KCP.priceCalulation.priceCalculation.dto.PriceCalculationResponse;
import com.Nixagh.Learn.KCP.priceCalulation.priceCalculation.process.PriceCalculationProcess;
import com.Nixagh.Learn.common.process.AbsProcess;
import com.Nixagh.Learn.common.webService.AbsWebService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceCalCalculationService extends AbsWebService {
  @PostMapping("priceCalculation")
  public PriceCalculationResponse create(@RequestBody PriceCalculationRequest request) {
    return (PriceCalculationResponse) super.executeProcess(request);
  }

  @Override
  protected AbsProcess getProcess() {
    return new PriceCalculationProcess();
  }
}
