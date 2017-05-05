package io.resourcepool.hvsz.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

  /**
   * Get the index page.
   * @return String (index vue)
   */
  @GetMapping("/")
  public String index() {
    return "index";
  }

}
