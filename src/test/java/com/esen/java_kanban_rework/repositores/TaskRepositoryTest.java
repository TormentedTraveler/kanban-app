package com.esen.java_kanban_rework.repositores;

import com.esen.java_kanban_rework.entity.Board;
import com.esen.java_kanban_rework.entity.Task;
import com.esen.java_kanban_rework.repository.BoardRepository;
import com.esen.java_kanban_rework.repository.TaskRepository;
import com.esen.java_kanban_rework.utils.TaskStatus;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)  // JUnit 5 version of @RunWith
@SpringBootTest
@DataJpaTest
public class TaskRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void whenFindById_thenReturnTask() {
        Board board = Board.builder()
                .name("University tasks")
                .build();

        Board savedBoard = entityManager.persist(board);
        entityManager.flush();



        Task task = Task.builder()
                .board(boardRepository.findById(1L)
                    .orElseThrow(() -> new EntityNotFoundException("Board not found for init")))
                .title("Notify users about upcoming changes")
                .status(TaskStatus.TO_DO)
                .description("Need to notify system users about upcoming changes to UI of the app")
                .build();
        Task savedTask = entityManager.persist(task);
        entityManager.flush();

        // When
        Optional<Task> foundTask = taskRepository.findById(savedTask.getId());

        // Then
        assertTrue(foundTask.isPresent());
        assertEquals(task.getTitle(), foundTask.get().getTitle());
    }
}
