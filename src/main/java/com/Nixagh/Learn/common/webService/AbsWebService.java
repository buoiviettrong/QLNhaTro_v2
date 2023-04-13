package com.Nixagh.Learn.common.webService;

import com.Nixagh.Learn.common.dto.request.AbsRequest;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import com.Nixagh.Learn.common.process.AbsProcess;

public abstract class AbsWebService {
  public AbsResponse executeProcess(AbsRequest request) {
    AbsResponse response = null;

    AbsProcess process = getProcess();
    response = process.execute(request);

    return response;
  }

  protected abstract AbsProcess getProcess();
}
