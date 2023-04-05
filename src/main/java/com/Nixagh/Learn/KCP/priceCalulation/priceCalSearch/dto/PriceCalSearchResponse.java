package com.Nixagh.Learn.KCP.priceCalulation.priceCalSearch.dto;

import com.Nixagh.Learn.common.dto.response.AbsResponse;

import java.util.ArrayList;

public class PriceCalSearchResponse extends AbsResponse {
  public ArrayList<PriceCalSearchRow> rows = new ArrayList<PriceCalSearchRow>();
}
