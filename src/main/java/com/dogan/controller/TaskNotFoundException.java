package com.dogan.controller;

public class TaskNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TaskNotFoundException(long id) {
		super("Task Not Found. Task Id: " + id);
	}
}
