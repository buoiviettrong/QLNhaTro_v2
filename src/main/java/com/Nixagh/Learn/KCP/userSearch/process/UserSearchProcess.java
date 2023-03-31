package com.Nixagh.Learn.KCP.userSearch.process;

import java.util.ArrayList;

import com.Nixagh.Learn.KCP.user.userSearch.UserSearchRows;
import com.Nixagh.Learn.KCP.userSearch.dto.UserSearchRequest;
import com.Nixagh.Learn.KCP.userSearch.dto.UserSearchResponse;
import com.Nixagh.Learn.common.dto.request.AbsRequest;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import com.Nixagh.Learn.common.process.AbsProcess;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class UserSearchProcess extends AbsProcess{
	@Override
	public UserSearchResponse process(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {
		UserSearchRequest userSearchRequest = (UserSearchRequest) request;
		UserSearchResponse userSearchResponse = (UserSearchResponse) response;
		
		// Get param
		String name = userSearchRequest.userSearchConditions.name;
		int status = userSearchRequest.userSearchConditions.status;

		// Prepare Query String
		Query query = new Query();
		query.addCriteria(Criteria.where("username").regex(name, "i"));
		
		// Query
		userSearchResponse.rows = (ArrayList<UserSearchRows>) mongoTemplate.find(query, UserSearchRows.class, "User");;

		// Return
		return userSearchResponse;
	}
	@Override
	protected UserSearchResponse createNewResponse() {
		return new UserSearchResponse();
	}
}
