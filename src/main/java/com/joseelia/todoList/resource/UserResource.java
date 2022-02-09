package com.joseelia.todoList.resource;

import com.joseelia.todoList.model.Task;
import com.joseelia.todoList.model.User;
import com.joseelia.todoList.model.UserForm;
import com.joseelia.todoList.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserResource {

    private final UserService userService;

    @GetMapping("/get-users")
    public ResponseEntity<List<User>> getUsers() {

        return ResponseEntity.ok().body(userService.getUsers());
    }
}
