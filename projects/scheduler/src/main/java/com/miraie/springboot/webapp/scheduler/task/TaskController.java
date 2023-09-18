package com.miraie.springboot.webapp.scheduler.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
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
    @RequestMapping(value = "addTask", method = RequestMethod.GET)
    public String showAddTask() {
        return "task";
    }
    @RequestMapping(value = "addTask", method = RequestMethod.POST)
    public String addTask(@RequestParam String description, ModelMap modelMap) {
        String userName =(String) modelMap.get("name");
        service.addTask(userName, description, LocalDate.now().plusYears(1), false);
        //Redirecting to the list of the tasks : Note : use the url and not jsp page name
        return "redirect:tasks";
    }
}
