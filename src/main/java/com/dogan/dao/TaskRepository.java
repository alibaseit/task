package com.dogan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dogan.model.Task;

@Repository //RestResource(collectionResourceRel = "task", path = "task")
public interface TaskRepository extends CrudRepository<Task, Long> {

	@Query(value = "select * from t_tasks where finished = true", nativeQuery = true)
	public List<Task> allCompletedTasks();

	@Query(value = "select name, description from t_tasks where finished = false", nativeQuery = true)
	public List<Task> allPendingTasks();

	public List<Task> findById(long id);
}
