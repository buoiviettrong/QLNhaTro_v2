package com.Nixagh.Learn.KCP.room.roomCheckout.webService;

import com.Nixagh.Learn.KCP.room.roomCheckout.dto.RoomCheckoutRequest;
import com.Nixagh.Learn.KCP.room.roomCheckout.dto.RoomCheckoutResponse;
import com.Nixagh.Learn.KCP.room.roomCheckout.process.RoomCheckoutProcess;
import com.Nixagh.Learn.common.process.AbsProcess;
import com.Nixagh.Learn.common.webService.AbsWebService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomCheckoutWebService extends AbsWebService {
  @PostMapping("roomCheckout")
  public RoomCheckoutResponse create(@RequestBody RoomCheckoutRequest request) {
    return (RoomCheckoutResponse) super.executeProcess(request);
  }

  @Override
  protected AbsProcess getProcess() {
    return new RoomCheckoutProcess();
  }
}
