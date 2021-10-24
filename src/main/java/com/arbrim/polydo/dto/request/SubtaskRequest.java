package com.arbrim.polydo.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class SubtaskRequest {

    @NotBlank(message = "Title should not be empty.")
    private String title;

    private String description;
    private Boolean completed;
    private LocalDateTime expectedDate;

    @NotNull(message = "Task id should not be empty. Subtask has to belong to some task.")
    private Long taskId;

}
