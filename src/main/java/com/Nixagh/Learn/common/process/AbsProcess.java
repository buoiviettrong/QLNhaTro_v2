package com.Nixagh.Learn.common.process;

import com.Nixagh.Learn.KCP.userSearch.dto.UserSearchResponse;
import com.Nixagh.Learn.common.database.DBAccessor;
import com.Nixagh.Learn.common.dto.request.AbsRequest;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.sql.Time;
import java.util.Date;

public abstract class AbsProcess {
	public AbsProcess() {};
	public AbsResponse execute(AbsRequest request) {
		return execute(null, request);
	}

	public AbsResponse execute(MongoTemplate mongoTemplate, AbsRequest request) {
		AbsResponse response = createNewResponse(request);

		if(mongoTemplate == null) mongoTemplate = DBAccessor.getDBAccessor();

		checkAuth(mongoTemplate, request, response);

		beforeProcess(request, response);
		process(mongoTemplate, request, response);
		afterProcess(request, response);
			
		return response;
	}

	protected void afterProcess(AbsRequest request,
			AbsResponse response){};

	protected AbsResponse process(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {return null;};

	protected void beforeProcess(AbsRequest request,
			AbsResponse response) {};

	protected AbsResponse createNewResponse(AbsRequest request) {
		return new AbsResponse();
	};

	public AbsResponse checkAuth(MongoTemplate mongoTemplate, AbsRequest request, AbsResponse response) {
		String token = request.accessInfo.token;

		Date date = new Date();
		Time time = new Time(date.getTime());

		System.out.println(date);
		System.out.println(time.toString());

		return response;
	}

}
