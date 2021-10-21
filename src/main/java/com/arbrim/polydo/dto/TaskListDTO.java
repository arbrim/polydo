package com.arbrim.polydo.dto;

import com.arbrim.polydo.model.Task;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class TaskListDTO {
    private Long id;
    private String name;
    private String description;
    private Set<Task> tasks;
}
