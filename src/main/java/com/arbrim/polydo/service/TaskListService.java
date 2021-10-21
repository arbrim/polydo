package com.arbrim.polydo.service;

import com.arbrim.polydo.dto.TaskListDTO;
import com.arbrim.polydo.model.Task;
import com.arbrim.polydo.model.TaskList;
import com.arbrim.polydo.repository.TaskListRepository;
import com.arbrim.polydo.repository.TaskRepository;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TaskListService {

    private Mapper mapper;
    private TaskRepository taskRepository;
    private TaskListRepository taskListRepository;

    public TaskListService(Mapper mapper, TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.mapper = mapper;
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    public List<TaskListDTO> getAll() {
        List<TaskList> tasklists = taskListRepository.findAll();

        return tasklists.stream().map(taskList -> mapper.map(taskList, TaskListDTO.class))
                .collect(Collectors.toList());
    }

    public TaskListDTO create(TaskListDTO taskListDTO) {
        TaskList taskList = mapper.map(taskListDTO, TaskList.class);
        return mapper.map(taskListRepository.save(taskList), TaskListDTO.class);
    }

    public void deleteTaskList(Long taskListId) throws Exception {
        validateTaskListExistenceOnTaskListDeletion(taskListId);
        Set<Task> tasks = taskRepository.findAllByTaskListId(taskListId);
        childTasksTaskIdsToNull(tasks);
        taskListRepository.deleteById(taskListId);
    }

    private void childTasksTaskIdsToNull(Set<Task> tasks) {
        tasks.forEach(task -> task.setTaskListId(null));
        taskRepository.saveAll(tasks);
    }

    private void validateTaskListExistenceOnTaskListDeletion(Long taskListId) throws Exception {
        boolean taskListExists = taskListRepository.existsById(taskListId);
        if(!taskListExists)
            throw new Exception(String.format("TaskList by id [%s] doesn't exist, thus can't be deleted.",
                    taskListId));
    }

    public TaskListDTO update(TaskListDTO taskListDTO, Long taskListId) throws Exception {
        validateTaskListOnUpdation(taskListDTO, taskListId);
        TaskList taskList = mapper.map(taskListDTO, TaskList.class);
        taskListRepository.save(taskList);
        return taskListDTO;
    }

    private void validateTaskListOnUpdation(TaskListDTO taskListDTO, Long taskListId) throws Exception {
        validateTaskListIdsOnTaskListUpdation(taskListDTO, taskListId);
        validateTaskListExistenceOnTaskListUpdation(taskListId);
    }

    private void validateTaskListExistenceOnTaskListUpdation(Long taskListId) throws Exception {
        if(!taskListRepository.existsById(taskListId))
            throw new Exception(String.format("Task by id [%s] doesn't exist.", taskListId));
    }

    private void validateTaskListIdsOnTaskListUpdation(TaskListDTO taskListDTO, Long taskListId) throws Exception {
        if(!taskListDTO.getId().equals(taskListId))
            throw new Exception(String.format("Id mismatch. TaskList id from update request [%s] and path variable [%s] is not the same.", taskListDTO.getId(), taskListId));
    }
}
