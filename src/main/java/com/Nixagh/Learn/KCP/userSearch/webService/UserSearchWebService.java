package com.Nixagh.Learn.KCP.userSearch.webService;

import com.Nixagh.Learn.KCP.userSearch.dto.UserSearchRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.Nixagh.Learn.KCP.userSearch.dto.UserSearchResponse;
import com.Nixagh.Learn.KCP.userSearch.process.UserSearchProcess;
import com.Nixagh.Learn.common.dto.request.AbsRequest;
import com.Nixagh.Learn.common.dto.response.AbsResponse;
import com.Nixagh.Learn.common.process.AbsProcess;
import com.Nixagh.Learn.common.webService.AbsWebService;

@RestController
public class UserSearchWebService extends AbsWebService{

	@PostMapping("userSearch")
	public UserSearchResponse search(@RequestBody UserSearchRequest request) {
		AbsResponse abs = super.executeProcess(request);

		return (UserSearchResponse) abs;
	}
	
	@Override
	protected AbsProcess getProcess() {
		return new UserSearchProcess();
	}

}
