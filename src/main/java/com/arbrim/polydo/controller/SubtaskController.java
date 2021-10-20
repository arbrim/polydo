package com.arbrim.polydo.controller;

import com.arbrim.polydo.model.Subtask;
import com.arbrim.polydo.service.SubtaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/subtasks")
public class SubtaskController {

    private SubtaskService subtaskService;

    public SubtaskController(SubtaskService subtaskService) {
        this.subtaskService = subtaskService;
    }

    @GetMapping("/")
    public List<Subtask> getAllSubtasks() {
        return subtaskService.getAll();
    }

}
