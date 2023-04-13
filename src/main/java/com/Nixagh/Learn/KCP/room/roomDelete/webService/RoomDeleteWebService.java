package com.Nixagh.Learn.KCP.room.roomDelete.webService;

import com.Nixagh.Learn.KCP.room.roomDelete.dto.RoomDeleteRequest;
import com.Nixagh.Learn.KCP.room.roomDelete.dto.RoomDeleteResponse;
import com.Nixagh.Learn.KCP.room.roomDelete.process.RoomDeleteProcess;
import com.Nixagh.Learn.common.process.AbsProcess;
import com.Nixagh.Learn.common.webService.AbsWebService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomDeleteWebService extends AbsWebService {
  @PostMapping("roomDelete")
  public RoomDeleteResponse create(@RequestBody RoomDeleteRequest request) {
    return (RoomDeleteResponse) super.executeProcess(request);
  }

  @Override
  protected AbsProcess getProcess() {
    return new RoomDeleteProcess();
  }
}
