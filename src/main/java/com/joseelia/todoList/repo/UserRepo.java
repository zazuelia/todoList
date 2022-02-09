package com.joseelia.todoList.repo;

import com.joseelia.todoList.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findUserByUserName(String userName);
}
