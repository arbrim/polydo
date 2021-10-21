package com.arbrim.polydo.dto;

import com.arbrim.polydo.model.Subtask;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO extends BaseTaskDTO{

    private Set<Subtask> subtasks;
    private Long taskListId;
}
