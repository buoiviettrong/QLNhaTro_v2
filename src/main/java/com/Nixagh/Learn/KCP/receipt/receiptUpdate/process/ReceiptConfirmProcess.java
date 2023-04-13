package com.Nixagh.Learn.KCP.receipt.receiptUpdate.process;

import com.Nixagh.Learn.KCP.receipt.receiptUpdate.dto.ReceiptConfirmRequest;
import com.Nixagh.Learn.KCP.receipt.receiptUpdate.dto.ReceiptConfirmResponse;
import com.Nixagh.Learn.common.dto.errorDto;
import com.Nixagh.Learn.common.dto.request.AbsRequest;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import com.Nixagh.Learn.common.process.AbsProcess;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class ReceiptConfirmProcess extends AbsProcess {
  @Override
  public void beforeProcess(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {
    ReceiptConfirmRequest requestReceiptConfirm = (ReceiptConfirmRequest) request;
    ReceiptConfirmResponse receiptConfirmResponse = (ReceiptConfirmResponse) response;
    String id = requestReceiptConfirm.receiptConfirmDto.id;
    double amountPaid = requestReceiptConfirm.receiptConfirmDto.amountPaid;

    Query query = new Query();
    query.addCriteria(Criteria.where("_id").is(new ObjectId(id)));
    ReceiptResult result = mongoTemplate.findOne(query, ReceiptResult.class, "Receipt");

    if (result == null)
      receiptConfirmResponse.addError(new errorDto("RECEIPT_CONFIRMATION", "ReceiptConfirm failed"));
    else if (result.remainingMoney < amountPaid)
      receiptConfirmResponse.addError(new errorDto("RECEIPT_CONFIRMATION", "Số Tiền Nhập Vào Vượt Quá Số Tiền Còn lại"));
    System.out.println(amountPaid + " " + result.totalRevenue + " " + result.remainingMoney);
  }

  @Override
  public ReceiptConfirmResponse process(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {
    ReceiptConfirmRequest requestReceiptConfirm = (ReceiptConfirmRequest) request;
    ReceiptConfirmResponse receiptConfirmResponse = (ReceiptConfirmResponse) response;
    String id = requestReceiptConfirm.receiptConfirmDto.id;
    double amountPaid = requestReceiptConfirm.receiptConfirmDto.amountPaid;
    try {
      Query query = new Query();
      query.addCriteria(Criteria.where("_id").is(new ObjectId(id)));
      ReceiptResult result = mongoTemplate.findOne(query, ReceiptResult.class, "Receipt");

      Update update = new Update();
      update.set("remainingMoney", result.remainingMoney - amountPaid);
      mongoTemplate.updateFirst(query, update, "Receipt");
    } catch (Exception e) {
      receiptConfirmResponse.addError(new errorDto("RECEIPT_CONFIRMATION", "ReceiptConfirm failed in processing"));
    }
    return receiptConfirmResponse;
  }

  public ReceiptConfirmResponse createNewResponse() {
    return new ReceiptConfirmResponse();
  }

  public class ReceiptResult {
    public double totalRevenue;
    public double remainingMoney;
  }
}
