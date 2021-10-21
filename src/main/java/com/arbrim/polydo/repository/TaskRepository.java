package com.arbrim.polydo.repository;

import com.arbrim.polydo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Set<Task> findAllByTaskListId(Long taskListId);
}
