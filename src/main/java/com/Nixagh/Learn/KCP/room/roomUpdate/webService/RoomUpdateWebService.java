package com.Nixagh.Learn.KCP.room.roomUpdate.webService;

import com.Nixagh.Learn.KCP.room.roomUpdate.dto.RoomUpdateRequest;
import com.Nixagh.Learn.KCP.room.roomUpdate.dto.RoomUpdateResponse;
import com.Nixagh.Learn.KCP.room.roomUpdate.process.RoomUpdateProcess;
import com.Nixagh.Learn.common.process.AbsProcess;
import com.Nixagh.Learn.common.webService.AbsWebService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomUpdateWebService extends AbsWebService {
  @PostMapping("roomUpdate")
  public RoomUpdateResponse search(@RequestBody RoomUpdateRequest request) {
    return (RoomUpdateResponse) super.executeProcess(request);
  }

  @Override
  protected AbsProcess getProcess() {
    return new RoomUpdateProcess();
  }
}
