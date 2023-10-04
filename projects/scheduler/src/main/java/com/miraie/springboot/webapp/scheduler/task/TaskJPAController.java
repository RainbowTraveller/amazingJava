package com.miraie.springboot.webapp.scheduler.task;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;


@Controller
@SessionAttributes("name")
public class TaskJPAController {
    @Autowired
    private TaskService service;


    @Autowired
    private TaskRepository taskRepository;

    public TaskJPAController (TaskService taskService, TaskRepository taskRepository) {
        super();
        this.service = taskService;
        this.taskRepository = taskRepository;
    }

    @RequestMapping("tasks")
    public String tasks(ModelMap modelMap) {
        String userName = getUserName(modelMap);
        List<Task> tasks = taskRepository.getByUserName(userName);
        modelMap.addAttribute("tasks", tasks);
        return "taskList";
    }


    @RequestMapping(value = "addTask", method = RequestMethod.GET)
    public String showAddTask(ModelMap modelMap) {
        String userName = getUserName(modelMap);
        //This is binding from the Controller to view
        //The "Default Desc" will show up in the view
        Task task = new Task(0, userName, "", LocalDate.now().plusYears(1), false);
        modelMap.put("task", task);
        return "task";
    }

    /*
        1. If validation is only added in the bean e.g. Task, it won't work.
        so need to add @Valid tag here
        2. Additional attribute : Binding result is also needed if we don't want to redirect page and
        display errors on the web page

     */
    @RequestMapping(value = "addTask", method = RequestMethod.POST)
    public String addTask(ModelMap modelMap, @Valid Task task, BindingResult result) {
        if (result.hasErrors()) {
            // Do not redirect but just be on the same page
            // But needs addition in the jsp page to show errors
            return "task";
        }
        String userName = getUserName(modelMap);
        task.setUserName(userName);
        //This is another way binding, whatever is entered in the view will be passed here
        taskRepository.save(task);
        //Redirecting to the list of the tasks : Note : use the url and not jsp page name
        return "redirect:tasks";
    }

    @RequestMapping("deleteTask")
    public String deleteTask(@RequestParam int id) {
        taskRepository.deleteById(id);
        return "redirect:tasks";
    }

    @RequestMapping(value = "updateTask", method = RequestMethod.GET)
    public String updateTask(@RequestParam int id, ModelMap modelMap) {
        //Get the task using id
        Task task = taskRepository.findById(id).get();
        //Populate in the model and redirect to appropriate page for editing
        modelMap.addAttribute("task", task);
        return "task";
    }
    @RequestMapping(value = "updateTask", method = RequestMethod.POST)
    public String saveUpdatedTask(ModelMap modelMap, @Valid Task task, BindingResult result) {
        if (result.hasErrors()) {
            // Do not redirect but just be on the same page
            // But needs addition in the jsp page to show errors
            return "task";
        }
        String userName = getUserName(modelMap);
        task.setUserName(userName);
        //This is another way binding, whatever is entered in the view will be passed here
        taskRepository.save(task);
        //Redirecting to the list of the tasks : Note : use the url and not jsp page name
        return "redirect:tasks";
    }
    private String getUserName (ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
