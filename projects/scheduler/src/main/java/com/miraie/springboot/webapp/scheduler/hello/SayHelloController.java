package com.miraie.springboot.webapp.scheduler.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SayHelloController {
    @RequestMapping("greet")
    @ResponseBody
    public String sendMessage() {
        return "Hello World ! What are you upto ?";
    }

    @RequestMapping("html-greet")
    @ResponseBody
    public String sendHtmlMessage() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("<html>");
        buffer.append("<head>");
        buffer.append("<title>Basic Web Page for Spring Demo</title>");
        buffer.append("</head>");
        buffer.append("<body>");
        buffer.append("<h1 style=\"border:2px solid DodgerBlue;\">Hello World</h1>");
        buffer.append("<b>Body of the HTML page for <u>Spring</u> Web App Project</b>");
        buffer.append("</body>");
        buffer.append("</html>");
        return  buffer.toString();
    }
    @RequestMapping("jsp-greet")
    public String sendJspMessage() {
        return  "hello";
    }
}
