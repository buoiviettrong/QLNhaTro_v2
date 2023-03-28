package com.Nixagh.Learn.common.dto.response;

import java.util.ArrayList;

import com.Nixagh.Learn.common.dto.errorDto;

public class AbsResponse {
	protected ArrayList<errorDto> errorList = new ArrayList<errorDto>();
	public ArrayList<errorDto> getErrorList() { return errorList; }
	public void setErrorList(ArrayList<errorDto> errorList) { this.errorList = errorList; }
	public void addError(errorDto error) { errorList.add(error); }
	public void addAllError(ArrayList<errorDto> errorList) { errorList.addAll(errorList); }
}
