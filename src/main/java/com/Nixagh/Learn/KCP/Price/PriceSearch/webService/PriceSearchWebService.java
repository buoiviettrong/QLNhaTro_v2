package com.Nixagh.Learn.KCP.Price.PriceSearch.webService;

import com.Nixagh.Learn.KCP.Price.PriceSearch.dto.PriceSearchRequest;
import com.Nixagh.Learn.KCP.Price.PriceSearch.dto.PriceSearchResponse;
import com.Nixagh.Learn.KCP.Price.PriceSearch.process.PriceSearchProcess;
import com.Nixagh.Learn.common.process.AbsProcess;
import com.Nixagh.Learn.common.webService.AbsWebService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceSearchWebService extends AbsWebService {
  @PostMapping("priceSearch")
  public PriceSearchResponse update(@RequestBody PriceSearchRequest request) {
    return (PriceSearchResponse) super.executeProcess(request);
  }

  @Override
  protected AbsProcess getProcess() {
    return new PriceSearchProcess();
  }
}
