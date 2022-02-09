package com.joseelia.todoList.service;

import com.joseelia.todoList.model.Role;
import com.joseelia.todoList.model.Task;
import com.joseelia.todoList.model.User;
import com.joseelia.todoList.repo.RoleRepo;
import com.joseelia.todoList.repo.TaskRepo;
import com.joseelia.todoList.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor @Slf4j @Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final TaskRepo taskRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findUserByUserName(username);

        if(user == null) {
            log.error("User {} not found in the database", username);
            throw new UsernameNotFoundException("User not found in the database");
        }

        log.info("User {} found in the database", username);

        Collection<SimpleGrantedAuthority> authorities = user.getRoles().stream().map(Role::getRoleName).map(map -> new SimpleGrantedAuthority(map)).collect(Collectors.toList());
        org.springframework.security.core.userdetails.User userDet = new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);
        return userDet;
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving user {} to the databaase", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public User getUser(String userName) {
        return userRepo.findUserByUserName(userName);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {

        log.info("Adding role {} to user {}", roleName, userName);
        User user = userRepo.findUserByUserName(userName);
        Role role = roleRepo.findRoleByRoleName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving role {}", role.getRoleName());
        return roleRepo.save(role);
    }

    @Override
    public Task saveTask(Task task) {
        log.info("Saving task {}", task.getTaskName());
        return taskRepo.save(task);
    }

    @Override
    public void addTaskToUser(String userName, String taskName) {
        log.info("Adding task {} to user {}", taskName, userName);
        User user = userRepo.findUserByUserName(userName);
        Task task = taskRepo.findTaskByTaskName(taskName);
        user.getTasks().add(task);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepo.findAll();
    }

    @Override
    public void deleteTaskByTaskId(Integer taskId) {

        log.info("Deleting task {}", taskId);
        taskRepo.deleteById(taskId);
    }
}
