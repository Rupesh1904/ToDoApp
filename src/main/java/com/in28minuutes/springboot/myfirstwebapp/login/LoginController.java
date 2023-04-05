package com.in28minuutes.springboot.myfirstwebapp.login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
  
    private AuthenticationService authenticateService;

    public LoginController(AuthenticationService authenticateService) {
        super();
        this.authenticateService = authenticateService;
    }
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String Login(){
        return "Login";
    }
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String WelcomePage(@RequestParam String name,
                                @RequestParam String password, ModelMap model){
            if(authenticateService.authenticate(name, password)){
                model.put("name",name);
                model.put("password", password);
                 return "Welcome";
            }
            model.put("error", "oops! Invalid Credentials! Please try again ");
            return "Login";
    }
}
