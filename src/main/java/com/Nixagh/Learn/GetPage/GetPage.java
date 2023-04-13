package com.Nixagh.Learn.GetPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetPage {
  @GetMapping("home")
  public String home() {
    return "room";
  }

  @GetMapping("login")
  public String login() {
    return "login";
  }

  @GetMapping("errorPage")
  public String errorPage() {
    return "errorPage";
  }

  @GetMapping("register")
  public String register() {
    return "register";
  }

  @GetMapping("room")
  public String room() {
    return "room";
  }

  @GetMapping("customer")
  public String customer() {
    return "customer";
  }

  @GetMapping("receipt")
  public String admin() {
    return "receipt";
  }

  @GetMapping("priceCal")
  public String priceCal() {
    return "priceCal";
  }


}
