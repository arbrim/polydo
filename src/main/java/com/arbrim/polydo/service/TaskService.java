package com.arbrim.polydo.service;

import com.arbrim.polydo.model.Task;
import com.arbrim.polydo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task getById(Long id){
        return taskRepository.getById(id);
    }

    public List<Task> getAll(){
        return taskRepository.findAll();
    }
}
