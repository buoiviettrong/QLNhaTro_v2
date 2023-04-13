package com.Nixagh.Learn.KCP.receipt.receiptCreate.process;

import com.Nixagh.Learn.KCP.receipt.receiptCreate.dto.ReceiptCreateRequest;
import com.Nixagh.Learn.KCP.receipt.receiptCreate.dto.ReceiptCreateResponse;
import com.Nixagh.Learn.common.dto.errorDto;
import com.Nixagh.Learn.common.dto.request.AbsRequest;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import com.Nixagh.Learn.common.process.AbsProcess;
import com.Nixagh.Learn.common.utilities.ConvertDate;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Date;

public class ReceiptCreateProcess extends AbsProcess {
  @Override
  public ReceiptCreateResponse process(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {
    ReceiptCreateRequest receiptCreateRequest = (ReceiptCreateRequest) request;
    ReceiptCreateResponse receiptCreateResponse = (ReceiptCreateResponse) response;
    receiptCreateRequest.receiptCreateDto.userId = receiptCreateRequest.accessInfo.userId;
    receiptCreateRequest.receiptCreateDto.timestamp = ConvertDate.convert(new Date(), "dd-MM-yyyy");
    receiptCreateRequest.receiptCreateDto.remainingMoney = receiptCreateRequest.receiptCreateDto.totalRevenue;
    try {
      mongoTemplate.save(receiptCreateRequest.receiptCreateDto, "Receipt");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      receiptCreateResponse.addError(new errorDto("RECEIPT_CREATE", "Receipt create failed"));
    }
    return receiptCreateResponse;
  }

  @Override
  public ReceiptCreateResponse createNewResponse() {
    return new ReceiptCreateResponse();
  }
}
