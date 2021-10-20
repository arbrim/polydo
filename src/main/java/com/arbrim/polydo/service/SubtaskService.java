package com.arbrim.polydo.service;

import com.arbrim.polydo.dto.SubtaskDTO;
import com.arbrim.polydo.model.Subtask;
import com.arbrim.polydo.repository.SubtaskRepository;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubtaskService {

    private Mapper mapper;
    private TaskService taskService;
    private SubtaskRepository subtaskRepository;

    public SubtaskService(Mapper mapper, TaskService taskService, SubtaskRepository subtaskRepository) {
        this.mapper = mapper;
        this.taskService = taskService;
        this.subtaskRepository = subtaskRepository;
    }

    public List<SubtaskDTO> getAll(){
        List<Subtask> subtasks = subtaskRepository.findAll();

        return subtasks.stream().map(subtask -> mapper.map(subtask, SubtaskDTO.class))
                .collect(Collectors.toList());
    }

    public List<SubtaskDTO> getAllByTaskId(Long taskId){
        List<Subtask> subtasksByTaskId = subtaskRepository.findAllByTaskId(taskId);
        return subtasksByTaskId.stream().map(subtask -> mapper.map(subtask, SubtaskDTO.class))
                .collect(Collectors.toList());
    }

    public SubtaskDTO create(SubtaskDTO subtaskDTO) throws Exception {
        validateSubtask(subtaskDTO);
        Subtask subtask = mapper.map(subtaskDTO, Subtask.class);
        return mapper.map(subtaskRepository.save(subtask), SubtaskDTO.class);
    }

    private void validateSubtask(SubtaskDTO subtaskDTO) throws Exception {
        boolean taskExistsById = taskService.existsById(subtaskDTO.getTaskId());
        if(!taskExistsById) {
            throw new Exception(String.format("Task by id [%s] doesn't exist.", subtaskDTO.getTaskId()));
        }

    }

}
