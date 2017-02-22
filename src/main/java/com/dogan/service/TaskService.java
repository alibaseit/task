package com.dogan.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogan.dao.TaskRepository;
import com.dogan.model.Task;

@Service
@Transactional
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public List<Task> findAll() {
		return (List<Task>) taskRepository.findAll();
	}

	public Task save(Task task) {
		return taskRepository.save(task);
	}

	public Task delete(long id) {
		Task task = taskRepository.findOne(id);
		taskRepository.delete(id);
		return task;
	}

	public List<Task> allCompletedTasks() {
		return taskRepository.allCompletedTasks();
	}

	public List<Task> allPendingTasks() {
		return taskRepository.allPendingTasks();
	}

	public List<Task> getTask(long id) {
		return taskRepository.findById(id);
	}
}
