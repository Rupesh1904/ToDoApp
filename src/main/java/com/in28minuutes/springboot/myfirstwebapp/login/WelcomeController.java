package com.in28minuutes.springboot.myfirstwebapp.login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class WelcomeController {


    @RequestMapping(value="/", method = RequestMethod.GET)
    public String goToWelcomePage( ModelMap model){
        model.put("name","rupesh");
        return "Welcome";
    }
}
