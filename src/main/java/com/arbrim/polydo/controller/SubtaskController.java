package com.arbrim.polydo.controller;

import com.arbrim.polydo.dto.SubtaskDTO;
import com.arbrim.polydo.request.SubtaskRequest;
import com.arbrim.polydo.request.SubtaskUpdateRequest;
import com.arbrim.polydo.service.SubtaskService;
import com.github.dozermapper.core.Mapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/subtasks")
public class SubtaskController {

    private Mapper mapper;
    private SubtaskService subtaskService;

    public SubtaskController(Mapper mapper, SubtaskService subtaskService) {
        this.mapper = mapper;
        this.subtaskService = subtaskService;
    }

    @GetMapping("/")
    public List<SubtaskDTO> getAllSubtasks() {
        return subtaskService.getAll();
    }

    @PostMapping("/")
    public SubtaskDTO createSubtask(@NotNull @Valid @RequestBody SubtaskRequest subtaskRequest) throws Exception {
        SubtaskDTO subtaskDTO = mapper.map(subtaskRequest, SubtaskDTO.class);
        return subtaskService.create(subtaskDTO);
    }

    @PutMapping("/{subtaskId}")
    public SubtaskDTO updateSubtask(@NotNull @Valid @RequestBody SubtaskUpdateRequest subtaskUpdateRequest, @PathVariable("subtaskId") Long subtaskId) throws Exception {
        SubtaskDTO subtaskDTO = mapper.map(subtaskUpdateRequest, SubtaskDTO.class);
        return subtaskService.update(subtaskDTO, subtaskId);
    }

    @DeleteMapping("/{subtaskId}")
    public void deleteSubtask(@PathVariable("subtaskId") Long subtaskId) throws Exception {
        subtaskService.deleteSubtask(subtaskId);
    }
}
