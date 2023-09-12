package com.miraie.springboot.webapp.scheduler.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {
    @Autowired
    private AuthenticationService authenticationService;
    //Adding logger
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        logger.debug("Inside the login method");
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String welcome(@RequestParam String name, @RequestParam String password, ModelMap model) {
        logger.debug("Inside the welcome method");
        if (authenticationService.authenticate(name, password)) {
            model.put("name", name);
            return "welcome";
        }

        model.put("errorMessage", "Invalid Credentials");
        return "login";
    }
}
