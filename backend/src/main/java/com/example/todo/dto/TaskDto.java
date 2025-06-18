package com.example.todo.dto;

import lombok.Data;

@Data
public class TaskDto {
    private String title;
    private String description;
    private boolean completed;
}
