package com.joseelia.todoList.resource;

import com.joseelia.todoList.model.Task;
import com.joseelia.todoList.model.User;
import com.joseelia.todoList.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/task")
@RequiredArgsConstructor
public class TaskResource {

    private final UserService userService;

    @PostMapping("/save-task")
    public ResponseEntity<Task> saveTask(@RequestBody Task task) {

        User user = userService.getUser(task.getUser().getUserName());
        Timestamp taskDate = new Timestamp(new Date().getTime());
        Timestamp lastUpdate = new Timestamp(new Date().getTime());
        task.getUser().setUserId(user.getUserId());
        task.setLastUpdate(lastUpdate);
        task.setTaskDate(taskDate);
        userService.saveTask(task);

        return ResponseEntity.ok().body(task);
    }

    @GetMapping("get-tasks/{userName}")
    public ResponseEntity<List<Task>> getTasks(@PathVariable String userName) {

        return ResponseEntity.ok().body(userService.getUser(userName).getTasks());
    }

    @DeleteMapping("delete-task/{taskId}")
    public ResponseEntity<Object> deleteTask(@PathVariable Integer taskId) {

        userService.deleteTaskByTaskId(taskId);

        return ResponseEntity.ok().build();
    }
}
