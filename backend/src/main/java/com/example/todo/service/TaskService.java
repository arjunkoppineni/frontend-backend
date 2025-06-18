package com.example.todo.service;

import com.example.todo.dto.TaskDto;
import com.example.todo.entity.Task;
import com.example.todo.entity.User;
import com.example.todo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task createTask(User user, TaskDto dto) {
        Task task = Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .completed(dto.isCompleted())
                .user(user)
                .build();
        return taskRepository.save(task);
    }

    public List<Task> getTasks(User user) {
        return taskRepository.findByUser(user);
    }

    public void deleteTask(Long taskId, User user) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }
        taskRepository.delete(task);
    }
}
