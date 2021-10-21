package com.arbrim.polydo.controller;

import com.arbrim.polydo.dto.TaskListDTO;
import com.arbrim.polydo.request.TaskListRequest;
import com.arbrim.polydo.request.TaskListUpdateRequest;
import com.arbrim.polydo.service.TaskListService;
import com.arbrim.polydo.service.TaskService;
import com.github.dozermapper.core.Mapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/tasklists")
public class TaskListController {

    private Mapper mapper;
    private TaskListService taskListService;

    public TaskListController(Mapper mapper, TaskService taskService, TaskListService taskListService) {
        this.mapper = mapper;
        this.taskListService = taskListService;
    }

    @GetMapping("/")
    public List<TaskListDTO> getAllTaskLists() {
        return taskListService.getAll();
    }

    @PostMapping("/")
    public TaskListDTO createTaskList(@NotNull @Valid @RequestBody TaskListRequest taskListRequest) {
        TaskListDTO taskListDTO = mapper.map(taskListRequest, TaskListDTO.class);
        return taskListService.create(taskListDTO);
    }

    @PutMapping("/{taskListId}")
    public TaskListDTO updateTaskList(@NotNull @Valid @RequestBody TaskListUpdateRequest taskListUpdateRequest, @PathVariable("taskListId") Long taskListId) throws Exception {
        TaskListDTO taskListDTO = mapper.map(taskListUpdateRequest, TaskListDTO.class);
        return taskListService.update(taskListDTO, taskListId);
    }

    @DeleteMapping("/{taskListId}")
    public void deleteTaskList(@PathVariable("taskListId") Long taskListId) throws Exception {
        taskListService.deleteTaskList(taskListId);
    }
}
