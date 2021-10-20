package com.arbrim.polydo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubtaskDTO {

    private Long id;
    private String title;
    private String description;
    private Boolean completed;
    private LocalDateTime expectedDate;
    private Long taskId;

}
