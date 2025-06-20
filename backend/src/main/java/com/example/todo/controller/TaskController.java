package com.example.todo.controller;

import com.example.todo.entity.Task;
import com.example.todo.entity.User;
import com.example.todo.service.TaskService;
import com.example.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Adjust to allow frontend domain
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getUserTasks(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(taskService.getTasksForUser(user));
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@AuthenticationPrincipal User user,
                                           @RequestBody Map<String, String> request) {
        String title = request.get("title");
        String description = request.get("description");
        Task task = taskService.createTask(user, title, description);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }
}
