package com.arbrim.polydo.repository;

import com.arbrim.polydo.model.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubtaskRepository extends JpaRepository<Subtask, Long> {

    List<Subtask> findAllByTaskId(Long taskId);

}
