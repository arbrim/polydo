package com.arbrim.polydo.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TaskListRequest {

    @NotBlank(message = "name should not be empty.")
    private String name;
    private String description;
}
