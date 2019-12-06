package org.sbybfai.ureport.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        return "Hello World!";
    }
}
