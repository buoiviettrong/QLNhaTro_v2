package com.Nixagh.Learn.GetPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetPage {
  @GetMapping("home")
  public String home() {
   return "index";
  }

  @GetMapping("login")
  public String login() {
   return "login";
  }

  @GetMapping("errorPage")
  public String errorPage() {
   return "errorPage";
  }

}
