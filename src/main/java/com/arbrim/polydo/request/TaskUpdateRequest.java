package com.arbrim.polydo.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class TaskUpdateRequest {

    @NotNull(message = "Task id should not be null.")
    private Long id;

    @NotBlank(message = "Title should not be empty.")
    private String title;

    private String description;
    private Boolean completed;
    private LocalDateTime expectedDate;
}
