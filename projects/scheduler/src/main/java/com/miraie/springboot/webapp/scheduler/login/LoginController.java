package com.miraie.springboot.webapp.scheduler.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    //Adding logger
    private Logger logger = LoggerFactory.getLogger(getClass());
    @RequestMapping("login")
    public String login(@RequestParam String name, ModelMap modelMap) {
        modelMap.put("username", name);
        logger.debug("Request Param supplied {}", name);
        return  "login";
    }
}
