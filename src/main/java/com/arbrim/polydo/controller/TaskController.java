package com.arbrim.polydo.controller;

import com.arbrim.polydo.model.Task;
import com.arbrim.polydo.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public List<Task> getAllTasks() {
        return taskService.getAll();
    }

    @GetMapping("/{id}")
    public Task getTaskById(Long id) {
        return taskService.getById(id);
    }

}
