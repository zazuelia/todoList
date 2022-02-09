package com.joseelia.todoList.repo;

import com.joseelia.todoList.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Integer> {

    Role findRoleByRoleName(String roleName);
}
