package com.miraie.springboot.restwebapp.survey.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// @Controller
@RestController
public class HelloWorld {
  @RequestMapping("/hello-world")
  // @ResponseBody : needed only if the @Controller is used. The @RestController combines both
  public String helloWorld() {
    return "Hello World";
  }

  @RequestMapping("/hello-world-bean")
  public HelloWorldBean helloWorldBean() {
    return new HelloWorldBean("Hello There ....Howdy...!");
  }
}
