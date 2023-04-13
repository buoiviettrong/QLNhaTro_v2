package com.Nixagh.Learn.KCP.priceCalulation.priceCalCreate.process;

import com.Nixagh.Learn.KCP.priceCalulation.priceCalCreate.dto.PriceCalCreateRequest;
import com.Nixagh.Learn.KCP.priceCalulation.priceCalCreate.dto.PriceCalCreateResponse;
import com.Nixagh.Learn.common.dto.errorDto;
import com.Nixagh.Learn.common.dto.request.AbsRequest;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import com.Nixagh.Learn.common.process.AbsProcess;
import com.Nixagh.Learn.common.utilities.ConvertDate;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Date;

public class PriceCalCreateProcess extends AbsProcess {
  @Override
  public PriceCalCreateResponse process(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {
    PriceCalCreateRequest priceCalCreateRequest = (PriceCalCreateRequest) request;
    PriceCalCreateResponse priceCalCreateResponse = (PriceCalCreateResponse) response;
    String userId = priceCalCreateRequest.accessInfo.userId;
    try {
      priceCalCreateRequest.priceCalCreateDto.timestamp = ConvertDate.convert(new Date(), "dd-MM-yyyy");
      priceCalCreateRequest.priceCalCreateDto.userId = userId;
      mongoTemplate.save(priceCalCreateRequest.priceCalCreateDto, "PriceCalculator");
    } catch (Exception e) {
      priceCalCreateResponse.addError(new errorDto("PRICE_CREATE", "Price create failed"));
    }
    return priceCalCreateResponse;
  }

  @Override
  public PriceCalCreateResponse createNewResponse() {
    return new PriceCalCreateResponse();
  }
}
