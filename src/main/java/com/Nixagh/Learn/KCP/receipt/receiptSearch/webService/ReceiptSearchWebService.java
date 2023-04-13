package com.Nixagh.Learn.KCP.receipt.receiptSearch.webService;

import com.Nixagh.Learn.KCP.receipt.receiptSearch.dto.ReceiptSearchRequest;
import com.Nixagh.Learn.KCP.receipt.receiptSearch.dto.ReceiptSearchResponse;
import com.Nixagh.Learn.KCP.receipt.receiptSearch.process.ReceiptSearchProcess;
import com.Nixagh.Learn.common.process.AbsProcess;
import com.Nixagh.Learn.common.webService.AbsWebService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiptSearchWebService extends AbsWebService {
  @PostMapping("receiptSearch")
  public ReceiptSearchResponse search(@RequestBody ReceiptSearchRequest request) {
    return (ReceiptSearchResponse) super.executeProcess(request);
  }

  @Override
  protected AbsProcess getProcess() {
    return new ReceiptSearchProcess();
  }
}
