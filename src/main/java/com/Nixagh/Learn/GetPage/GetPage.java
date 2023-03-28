package com.Nixagh.Learn.GetPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetPage {
  @GetMapping("home")
  public String home() {
   return "index";
  }
}
