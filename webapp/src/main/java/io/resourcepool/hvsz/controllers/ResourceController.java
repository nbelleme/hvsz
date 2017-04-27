package io.resourcepool.hvsz.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResourceController {

    @GetMapping("/resource/drop")
    public String resourceDrop(@RequestParam(value="resourcedrop", required=false) String resourceDrop, Model model) {
        if(resourceDrop != null) {
            model.addAttribute("resourcedrop", "Resource has been dropped");
        }
        return "human";
    }
    @GetMapping("/resource/get")
    public String resourceGet(@RequestParam(value="resourceget", required=false) String resourceGet, Model model) {
        if(resourceGet != null) {
            model.addAttribute("resourceget", "Resource has been taken");
        }
        return "supply-zone";
    }
}