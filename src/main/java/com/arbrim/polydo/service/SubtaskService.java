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
        validateSubtaskCreation(subtaskDTO);
        Subtask subtask = mapper.map(subtaskDTO, Subtask.class);
        return mapper.map(subtaskRepository.save(subtask), SubtaskDTO.class);
    }

    private void validateSubtaskCreation(SubtaskDTO subtaskDTO) throws Exception {
        validateTaskExistenceOnSubtaskCreation(subtaskDTO);
    }

    private void validateTaskExistenceOnSubtaskCreation(SubtaskDTO subtaskDTO) throws Exception {
        boolean taskExistsById = taskService.existsById(subtaskDTO.getTaskId());
        if(!taskExistsById) {
            throw new Exception(String.format("Task by id [%s] doesn't exist, subtask doesn't belong to any task thus can't be created.",
                    subtaskDTO.getTaskId()));
        }
    }

    public SubtaskDTO update(SubtaskDTO subtaskDTO, Long subtaskId) throws Exception {
        validateSubtaskUpdation(subtaskDTO, subtaskId);
        Subtask subtask = mapper.map(subtaskDTO, Subtask.class);
        subtaskRepository.save(subtask);
        return subtaskDTO;
    }

    private void validateSubtaskUpdation(SubtaskDTO subtaskDTO, Long subtaskId) throws Exception {
        validateSubtaskIdsOnSubtaskUpdation(subtaskDTO, subtaskId);
        validateSubtaskExistenceOnSubtaskUpdation(subtaskId);
        validateTaskExistenceOnSubtaskUpdation(subtaskDTO);
    }

    private void validateSubtaskIdsOnSubtaskUpdation(SubtaskDTO subtaskDTO, Long subtaskId) throws Exception {
        if(!subtaskDTO.getId().equals(subtaskId))
            throw new Exception(String.format("Id mismatch. Subtask id from update request [%s] and path variable [%s] is not the same.", subtaskDTO.getId(), subtaskId));
    }

    private void validateSubtaskExistenceOnSubtaskUpdation(Long subtaskId) throws Exception {
        boolean subtaskExists = subtaskRepository.existsById(subtaskId);
        if(!subtaskExists)
            throw new Exception(String.format("Subtask by id [%s] doesn't exist, thus can't be updated.",
                    subtaskId));
    }

    private void validateTaskExistenceOnSubtaskUpdation(SubtaskDTO subtaskDTO) throws Exception {
        Long taskId = subtaskDTO.getTaskId();
        boolean taskExists = taskService.existsById(taskId);
        if(!taskExists)
            throw new Exception(String.format("Task by id [%s] doesn't exist, subtask should belong to a Task, thus can't be updated.",
                    taskId));
    }

    public void deleteSubtask(Long subtaskId) throws Exception {
        validateSubtaskExistenceOnSubtaskDeletion(subtaskId);
        subtaskRepository.deleteById(subtaskId);
    }

    private void validateSubtaskExistenceOnSubtaskDeletion(Long subtaskId) throws Exception {
        boolean subtaskExists = subtaskRepository.existsById(subtaskId);
        if(!subtaskExists)
            throw new Exception(String.format("Subtask by id [%s] doesn't exist, thus can't be deleted.",
                    subtaskId));
    }
}
