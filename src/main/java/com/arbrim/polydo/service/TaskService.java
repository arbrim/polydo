package com.arbrim.polydo.service;

import com.arbrim.polydo.dto.TaskDTO;
import com.arbrim.polydo.model.Task;
import com.arbrim.polydo.repository.TaskRepository;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private Mapper mapper;
    private TaskRepository taskRepository;

    public TaskService(Mapper mapper, TaskRepository taskRepository) {
        this.mapper = mapper;
        this.taskRepository = taskRepository;
    }

    public TaskDTO getById(Long id) throws Exception {
        if(existsById(id))
            return mapper.map(taskRepository.getById(id), TaskDTO.class);
        throw new Exception(String.format("Task with id [%s] doesn't exist.", id));
    }

    public List<TaskDTO> getAll(){
        List<Task> allTasks = taskRepository.findAll();

        return allTasks.stream().map(task -> mapper.map(task, TaskDTO.class))
                .collect(Collectors.toList());
    }

    public TaskDTO createTask(TaskDTO taskDTO){
        Task task = mapper.map(taskDTO, Task.class);

        Task taskInDB = taskRepository.save(task);
        return mapper.map(taskInDB, TaskDTO.class);
    }

    public boolean existsById(Long id){
        return taskRepository.existsById(id);
    }
}
