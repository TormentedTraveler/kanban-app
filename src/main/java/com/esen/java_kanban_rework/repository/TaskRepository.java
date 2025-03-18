package com.esen.java_kanban_rework.repository;

import com.esen.java_kanban_rework.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByBoardId(Long boardId);
}
