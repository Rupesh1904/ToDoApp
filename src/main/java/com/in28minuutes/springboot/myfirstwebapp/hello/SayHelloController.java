package com.in28minuutes.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class SayHelloController {

    // src\main\resources\META-INF\resources\WEB-INF\jsp\sayHello.jsp
    @RequestMapping("/say-hello-jsp")

    public String sayHelloJsp(@RequestParam String title, ModelMap model){
        model.put("title", title);
        return "sayHello";
    }
}
