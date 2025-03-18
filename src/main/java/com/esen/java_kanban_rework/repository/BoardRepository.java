package com.esen.java_kanban_rework.repository;

import com.esen.java_kanban_rework.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOwnerId(Long ownerId);

}