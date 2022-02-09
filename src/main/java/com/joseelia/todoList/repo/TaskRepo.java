package com.joseelia.todoList.repo;

import com.joseelia.todoList.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Integer> {

    Task findTaskByTaskName(String taskName);
}
