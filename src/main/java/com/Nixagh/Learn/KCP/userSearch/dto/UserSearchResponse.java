package com.Nixagh.Learn.KCP.userSearch.dto;

import java.util.ArrayList;

import com.Nixagh.Learn.KCP.user.userSearch.UserSearchRows;
import com.Nixagh.Learn.common.dto.response.AbsResponse;

public class UserSearchResponse extends AbsResponse{
	public ArrayList<UserSearchRows> rows;
}
