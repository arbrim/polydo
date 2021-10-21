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

    public TaskDTO updateTask(TaskDTO taskDTO, Long taskId) throws Exception {
        validateTaskOnUpdation(taskDTO, taskId);
        Task task = mapper.map(taskDTO, Task.class);
        taskRepository.save(task);
        return taskDTO;
    }

    private void validateTaskOnUpdation(TaskDTO taskDTO, Long taskId) throws Exception {
        validateTaskIdsOnTaskUpdation(taskDTO, taskId);
        validateTaskExistenceOnTaskUpdation(taskDTO);
    }

    private void validateTaskExistenceOnTaskUpdation(TaskDTO taskDTO) throws Exception {
        if(!taskRepository.existsById(taskDTO.getId()))
            throw new Exception(String.format("Task by id [%s] doesn't exist.", taskDTO.getId()));
    }

    private void validateTaskIdsOnTaskUpdation(TaskDTO taskDTO, Long taskId) throws Exception {
        if(!taskDTO.getId().equals(taskId))
            throw new Exception(String.format("Id mismatch. Task id from update request [%s] and path variable [%s] is not the same.", taskDTO.getId(), taskId));
    }

    public void deleteTask(Long taskId) throws Exception {
        validateTaskExistenceOnDeletion(taskId);
        taskRepository.deleteById(taskId);
    }

    private void validateTaskExistenceOnDeletion(Long taskId) throws Exception {
        if(!existsById(taskId))
            throw new Exception(String.format("Task by id %s didn't exist before, thus cant be deleted.", taskId));
    }
}
