package com.esen.java_kanban_rework.utils;
import com.esen.java_kanban_rework.entity.Board;
import com.esen.java_kanban_rework.entity.Task;
import com.esen.java_kanban_rework.entity.User;
import com.esen.java_kanban_rework.repository.BoardRepository;
import com.esen.java_kanban_rework.repository.TaskRepository;
import com.esen.java_kanban_rework.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class BootstrapData implements CommandLineRunner {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final TaskRepository taskRepository;

    public BootstrapData(UserRepository userRepository, BoardRepository boardRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
        this.taskRepository = taskRepository;
    }

    private void createInitData() {
        User user1 = User.builder()
                .email("test@mail.com")
                .firstname("test")
                .lastname("test")
                .password("123456")
                .build();
        User user2 = User.builder()
                .email("test2@mail.com")
                .firstname("test2")
                .lastname("test2")
                .password("123456")
                .build();

        log.info("Creating boards");
        userRepository.saveAll(List.of(user1, user2));

        Board board1 = Board.builder()
                .name("University tasks")
                .build();

        Board board2 = Board.builder()
                .name("Job tasks")
                .build();

        log.info("Creating users");
        boardRepository.saveAll(List.of(board1, board2));

        Task task1 = Task.builder()
                .board(boardRepository.findById(1L)
                        .orElseThrow(() -> new EntityNotFoundException("Board not found for init")))
                .title("Do database homework")
                .status(TaskStatus.TO_DO)
                .description("Complete database methods")
                .build();

        Task task2 = Task.builder()
                .board(boardRepository.findById(1L)
                        .orElseThrow(() -> new EntityNotFoundException("Board not found for init")))
                .title("Do history homework")
                .status(TaskStatus.TO_DO)
                .description("Learn about Gorbachov")
                .build();

        Task task3 = Task.builder()
                .board(boardRepository.findById(2L)
                        .orElseThrow(() -> new EntityNotFoundException("Board not found for init")))
                .title("Notify users about upcoming changes")
                .status(TaskStatus.TO_DO)
                .description("Need to notify system users about upcoming changes to UI of the app")
                .build();

        Task task4 = Task.builder()
                .board(boardRepository.findById(2L)
                        .orElseThrow(() -> new EntityNotFoundException("Board not found for init")))
                .title("Add a new server")
                .status(TaskStatus.TO_DO)
                .description("New server needs to be added to our server pool")
                .build();

        log.info("Creating tasks");
        taskRepository.saveAll(List.of(task1, task2, task3, task4));
        return;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            createInitData();
        }
    }
}
