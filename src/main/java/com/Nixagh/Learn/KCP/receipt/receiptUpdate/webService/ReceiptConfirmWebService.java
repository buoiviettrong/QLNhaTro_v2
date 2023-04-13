package com.Nixagh.Learn.KCP.receipt.receiptUpdate.webService;

import com.Nixagh.Learn.KCP.receipt.receiptUpdate.dto.ReceiptConfirmRequest;
import com.Nixagh.Learn.KCP.receipt.receiptUpdate.dto.ReceiptConfirmResponse;
import com.Nixagh.Learn.KCP.receipt.receiptUpdate.process.ReceiptConfirmProcess;
import com.Nixagh.Learn.common.process.AbsProcess;
import com.Nixagh.Learn.common.webService.AbsWebService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiptConfirmWebService extends AbsWebService {
  @PostMapping("receiptConfirm")
  public ReceiptConfirmResponse confirm(@RequestBody ReceiptConfirmRequest request) {
    return (ReceiptConfirmResponse) super.executeProcess(request);
  }

  @Override
  protected AbsProcess getProcess() {
    return new ReceiptConfirmProcess();
  }
}
