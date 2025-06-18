package com.example.todo.controller;

import com.example.todo.dto.TaskDto;
import com.example.todo.entity.Task;
import com.example.todo.entity.User;
import com.example.todo.service.TaskService;
import com.example.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public Task createTask(@RequestBody TaskDto dto, @AuthenticationPrincipal User user) {
        return taskService.createTask(user, dto);
    }

    @GetMapping
    public List<Task> getTasks(@AuthenticationPrincipal User user) {
        return taskService.getTasks(user);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId, @AuthenticationPrincipal User user) {
        taskService.deleteTask(taskId, user);
    }
}
