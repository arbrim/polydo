package com.arbrim.polydo.repository;

import com.arbrim.polydo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
