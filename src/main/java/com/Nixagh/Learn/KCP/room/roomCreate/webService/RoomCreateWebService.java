package com.Nixagh.Learn.KCP.room.roomCreate.webService;

import com.Nixagh.Learn.KCP.room.roomCreate.dto.RoomCreateRequest;
import com.Nixagh.Learn.KCP.room.roomCreate.dto.RoomCreateResponse;
import com.Nixagh.Learn.KCP.room.roomCreate.process.RoomCreateProcess;
import com.Nixagh.Learn.common.process.AbsProcess;
import com.Nixagh.Learn.common.webService.AbsWebService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomCreateWebService extends AbsWebService {
  @PostMapping("roomCreate")
  public RoomCreateResponse create(@RequestBody RoomCreateRequest request) {
    return (RoomCreateResponse) super.executeProcess(request);
  }

  @Override
  protected AbsProcess getProcess() {
    return new RoomCreateProcess();
  }
}
