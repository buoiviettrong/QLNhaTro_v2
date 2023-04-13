package com.Nixagh.Learn.KCP.receipt.receiptCreate.webService;

import com.Nixagh.Learn.KCP.receipt.receiptCreate.dto.ReceiptCreateRequest;
import com.Nixagh.Learn.KCP.receipt.receiptCreate.dto.ReceiptCreateResponse;
import com.Nixagh.Learn.KCP.receipt.receiptCreate.process.ReceiptCreateProcess;
import com.Nixagh.Learn.common.process.AbsProcess;
import com.Nixagh.Learn.common.webService.AbsWebService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiptCreateWebService extends AbsWebService {
  @PostMapping("receiptCreate")
  public ReceiptCreateResponse search(@RequestBody ReceiptCreateRequest request) {
    return (ReceiptCreateResponse) super.executeProcess(request);
  }
  @Override
  protected AbsProcess getProcess() {
    return new ReceiptCreateProcess();
  }
}
