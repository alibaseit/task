package com.dogan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dogan.model.Task;
import com.dogan.service.TaskService;
@CrossOrigin
@RestController
@RequestMapping("/task")
public class TaskController extends BaseController {
	@Autowired
	private TaskService taskService;

	@GetMapping("/{id}")
	public List<Task> getTaskById(@PathVariable("id") long id) {
		return taskService.getTask(id);
	}

	@GetMapping("/unfinished")
	public List<Task> pendingTasks() {
		return taskService.allPendingTasks();
	}

	@GetMapping("/finished")
	public List<Task> completedTasks() {
		return taskService.allCompletedTasks();
	}

	@GetMapping
	public Iterable<Task> all() {
		return taskService.findAll();
	}

	@PostMapping
	public Task save(@RequestBody Task task) {
		return taskService.save(task);
	}

	@PutMapping
	public Task add(@RequestBody Task task) {
		return taskService.save(task);
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") long id) {
		taskService.delete(id);
		return "Deleted";
	}
}
