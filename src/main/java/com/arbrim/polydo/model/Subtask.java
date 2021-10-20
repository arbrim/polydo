package com.arbrim.polydo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Subtask extends BaseTask {

    @Column(name = "task_id")
    private Long taskId;

    @JsonBackReference
    @JoinColumn(name = "task_id", insertable = false, updatable = false)
    @ManyToOne
    private Task task;
}
