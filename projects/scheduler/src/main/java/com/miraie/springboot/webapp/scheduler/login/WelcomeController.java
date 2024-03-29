package com.miraie.springboot.webapp.scheduler.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {
    //Adding logger
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(ModelMap map) {
        logger.debug("Inside the login method");
        map.put("name", getLoggedInUser());
        return "welcome";
    }

    private String getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
