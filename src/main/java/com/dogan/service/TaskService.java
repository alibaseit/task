package com.dogan.service;

import java.time.Instant;
import java.util.Date;
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
		if (task.getDateCreated() == null)
			task.setDateCreated(Date.from(Instant.now()));
		if (taskRepository.exists(task.getId()))
			return taskRepository.save(task);
		else
			return taskRepository.save(task);
	}

	public void delete(long id) {
		taskRepository.delete(id);
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
