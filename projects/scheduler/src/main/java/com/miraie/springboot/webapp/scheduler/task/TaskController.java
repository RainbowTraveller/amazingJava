package com.miraie.springboot.webapp.scheduler.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;


@Controller
@SessionAttributes("name")
public class TaskController {
    @Autowired
    private TaskService service;

    public TaskController(TaskService taskService) {
        super();
        this.service = taskService;
    }

    @RequestMapping("tasks")
    public String tasks(ModelMap model) {
        List<Task> tasks = service.getByUserName("milo");
        model.addAttribute("tasks", tasks);
        return "taskList";
    }
}
