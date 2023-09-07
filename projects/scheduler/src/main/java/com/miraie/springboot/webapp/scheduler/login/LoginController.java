package com.miraie.springboot.webapp.scheduler.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    @RequestMapping("login")
    public String login(@RequestParam String name, ModelMap modelMap) {
        modelMap.put("username", name);
        return  "login";
    }
}
