package com.Nixagh.Learn.GetPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GetModal {
  @GetMapping("/modal/{name}")
  public String getModal(@PathVariable(name = "name") String name) {
    return name;
  }
}
