package com.arbrim.polydo.controller;

import com.arbrim.polydo.dto.SubtaskDTO;
import com.arbrim.polydo.dto.TaskDTO;
import com.arbrim.polydo.request.TaskRequest;
import com.arbrim.polydo.request.TaskUpdateRequest;
import com.arbrim.polydo.service.SubtaskService;
import com.arbrim.polydo.service.TaskService;
import com.github.dozermapper.core.Mapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private Mapper mapper;
    private TaskService taskService;
    private SubtaskService subtaskService;

    public TaskController(Mapper mapper, TaskService taskService, SubtaskService subtaskService) {
        this.mapper = mapper;
        this.taskService = taskService;
        this.subtaskService = subtaskService;
    }

    @GetMapping("/")
    public List<TaskDTO> getAllTasks() {
        return taskService.getAll();
    }

    @GetMapping("/{id}")
    public TaskDTO getTaskById(@PathVariable("id") Long id) throws Exception {
        return taskService.getById(id);
    }

    @PostMapping("/")
    public TaskDTO createTask(@NotNull @Valid @RequestBody TaskRequest taskRequest) {
        TaskDTO taskDTO = mapper.map(taskRequest, TaskDTO.class);
        return taskService.createTask(taskDTO);
    }

    @GetMapping("/{taskId}/subtasks")
    public List<SubtaskDTO> getAllSubtasksByTaskId(@PathVariable("taskId") Long taskId) {
        return subtaskService.getAllByTaskId(taskId);
    }

    @PutMapping("/{taskId}")
    public TaskDTO updateTask(@NotNull @Valid @RequestBody TaskUpdateRequest taskUpdateRequest, @PathVariable("taskId") Long taskId) throws Exception {
        TaskDTO taskDTO = mapper.map(taskUpdateRequest, TaskDTO.class);
        return taskService.updateTask(taskDTO, taskId);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable("taskId") Long taskId) throws Exception {
        taskService.deleteTask(taskId);
    }
}
