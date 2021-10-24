package com.arbrim.polydo.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TaskListUpdateRequest {

    @NotNull(message = "TaskList id should not be null.")
    private Long id;

    @NotBlank(message = "name should not be empty.")
    private String name;
    private String description;
}
