package com.miraie.springboot.restwebapp.survey.hello;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @Controller
@RestController
public class HelloWorldController {
  /**
   * Simple string return.
   * By default, the string parameter is considered to be JSP name.
   * To maintain the String, we need to have @ResponseBody annotation
   * @return plain java string
   */
  @RequestMapping("/hello-world")
  // @ResponseBody : needed only if the @Controller is used. The @RestController combines both
  public String helloWorld() {
    return "Hello World";
  }

  /**
   * Now we are returning a java bean or POJO.
   * This is not plain string. But the Spring starter, has dependency for Jackson2.
   * This helps convert object data to json by default. This can be observed if the logging level is set to
   * debug.
   * Using @RestController takes care of @ResponseBody
   * @return pojo as configured
   */
  @RequestMapping("/hello-world-bean")
  public HelloWorldBean helloWorldBean() {
    return new HelloWorldBean("Hello There ....Howdy...!");
  }

  /**
   *  Heating up REST protocol now. Adding a param in the path of the URL
   *  and handling it as a parameterized value
   * @param name this is a string as part of url and acts as variable for actual data passed
   * @return pojo
   */
  @RequestMapping("/hello-world-path-param/{name}")
  public HelloWorldBean helloWorldPathParamBean(@PathVariable String name) {
    return new HelloWorldBean("Hello There ....Howdy...! " + name);
  }
  @RequestMapping("/hello-world-path-param/{name}/messages/{message}")
  public HelloWorldBean helloWorldMultipleParamBean(@PathVariable String name, @PathVariable String message) {
    return new HelloWorldBean("Hello There ....Howdy...! " + name + " , " + message);
  }
}
