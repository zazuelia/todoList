package com.joseelia.todoList.service;

import com.joseelia.todoList.model.Role;
import com.joseelia.todoList.model.Task;
import com.joseelia.todoList.model.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    User getUser(String userName);
    void addRoleToUser(String userName, String roleName);
    Role saveRole(Role role);
    Task saveTask(Task task);
    void addTaskToUser(String userName, String taskName);
    List<User> getUsers();
    void deleteTaskByTaskId(Integer taskId);
}
