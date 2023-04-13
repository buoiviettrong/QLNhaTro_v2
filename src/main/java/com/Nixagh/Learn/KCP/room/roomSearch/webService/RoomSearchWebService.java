package com.Nixagh.Learn.KCP.room.roomSearch.webService;

import com.Nixagh.Learn.KCP.room.roomSearch.dto.RoomSearchRequest;
import com.Nixagh.Learn.KCP.room.roomSearch.dto.RoomSearchResponse;
import com.Nixagh.Learn.KCP.room.roomSearch.process.RoomSearchProcess;
import com.Nixagh.Learn.common.process.AbsProcess;
import com.Nixagh.Learn.common.webService.AbsWebService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomSearchWebService extends AbsWebService {
  @PostMapping("roomSearch")
  public RoomSearchResponse search(@RequestBody RoomSearchRequest request) {
    return (RoomSearchResponse) super.executeProcess(request);
  }

  @Override
  protected AbsProcess getProcess() {
    return new RoomSearchProcess();
  }
}
