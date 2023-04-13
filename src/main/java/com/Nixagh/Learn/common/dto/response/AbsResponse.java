package com.Nixagh.Learn.common.dto.response;

import com.Nixagh.Learn.common.dto.errorDto;

import java.util.ArrayList;

public class AbsResponse {
  protected ArrayList<errorDto> errorList = new ArrayList<errorDto>();

  public ArrayList<errorDto> getErrorList() {
    return errorList;
  }

  public void setErrorList(ArrayList<errorDto> errorList) {
    this.errorList = errorList;
  }

  public void addError(errorDto error) {
    errorList.add(error);
  }

  public void addAllError(ArrayList<errorDto> errorList) {
    errorList.addAll(errorList);
  }
}
