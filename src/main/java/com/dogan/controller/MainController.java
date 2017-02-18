package com.dogan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dogan.model.Task;
import com.dogan.service.TaskService;

@Controller
public class MainController extends BaseController {
    @Autowired 
    TaskService taskService;
    
	@RequestMapping("/k")
	public String home(Model model) {
		model.addAttribute("tasks", taskService.findAll());
		return "index";
	}

	@GetMapping("finishedtasks")
	@ResponseBody
	public List<Task> finishedTasks() {
		return taskService.allCompletedTasks();
	}
}
