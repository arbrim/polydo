package com.arbrim.polydo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task extends BaseTask {

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "task", cascade = CascadeType.ALL)
    private Set<Subtask> subtasks;

    @Column(name = "task_list_id")
    private Long taskListId;

    @JsonBackReference
    @JoinColumn(name = "task_list_id", insertable = false, updatable = false)
    @ManyToOne
    private TaskList taskList;

}
