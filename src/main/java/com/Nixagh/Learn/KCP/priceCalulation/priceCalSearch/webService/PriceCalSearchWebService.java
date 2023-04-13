package com.Nixagh.Learn.KCP.priceCalulation.priceCalSearch.webService;

import com.Nixagh.Learn.KCP.priceCalulation.priceCalSearch.dto.PriceCalSearchRequest;
import com.Nixagh.Learn.KCP.priceCalulation.priceCalSearch.dto.PriceCalSearchResponse;
import com.Nixagh.Learn.KCP.priceCalulation.priceCalSearch.process.PriceCalSearchProcess;
import com.Nixagh.Learn.common.process.AbsProcess;
import com.Nixagh.Learn.common.webService.AbsWebService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceCalSearchWebService extends AbsWebService {
  @PostMapping("priceCalSearch")
  public PriceCalSearchResponse create(@RequestBody PriceCalSearchRequest request) {
    return (PriceCalSearchResponse) super.executeProcess(request);
  }

  @Override
  protected AbsProcess getProcess() {
    return new PriceCalSearchProcess();
  }
}
