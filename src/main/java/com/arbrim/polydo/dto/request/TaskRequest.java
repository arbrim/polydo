package com.arbrim.polydo.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
public class TaskRequest {

    @NotBlank(message = "Title should not be empty.")
    private String title;

    private String description;
    private Boolean completed;
    private LocalDateTime expectedDate;
    private Long taskListId;
}
