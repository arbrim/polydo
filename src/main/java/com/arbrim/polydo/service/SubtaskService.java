package com.arbrim.polydo.service;

import com.arbrim.polydo.model.Subtask;
import com.arbrim.polydo.repository.SubtaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubtaskService {

    private SubtaskRepository subtaskRepository;

    public SubtaskService(SubtaskRepository subtaskRepository) {
        this.subtaskRepository = subtaskRepository;
    }

    public List<Subtask> getAll(){
        return subtaskRepository.findAll();
    }

    public List<Subtask> getAllByTaskId(Long taskId){
        return subtaskRepository.findAllByTaskId(taskId);
    }


}
