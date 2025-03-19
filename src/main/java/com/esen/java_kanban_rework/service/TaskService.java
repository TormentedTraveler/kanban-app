package com.esen.java_kanban_rework.service;


import com.esen.java_kanban_rework.dto.TaskDTO;
import com.esen.java_kanban_rework.entity.Board;
import com.esen.java_kanban_rework.entity.Task;
import com.esen.java_kanban_rework.repository.TaskRepository;
import com.esen.java_kanban_rework.exception_handlers.NoFieldsProvidedException;
import com.esen.java_kanban_rework.utils.TaskStatus;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final BoardService boardService;

    public TaskService(TaskRepository taskRepository, BoardService boardService) {
        this.taskRepository = taskRepository;
        this.boardService = boardService;
    }

    public void createTask(TaskDTO taskPostRequest) {
        Board board = boardService.getBoardById(taskPostRequest.getBoardId());
        Task task = Task.builder()
                .status(TaskStatus.TO_DO)
                .title(taskPostRequest.getTitle())
                .description(taskPostRequest.getDescription())
                .board(board)
                .build();
        taskRepository.save(task);
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(() -> new EntityNotFoundException("Task not found with id " + taskId));
    }

    public TaskDTO getTaskByIdForUser(Long taskId) {
        Task task = getTaskById(taskId);
        return TaskDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .boardId(task.getBoard().getId())
                .build();
    }

    public void deleteTask(Long taskId) {
        Task task = getTaskById(taskId);
        taskRepository.deleteById(taskId);
    }


    public void updateTask(Long taskId, TaskDTO updatedTaskFromUser) {
        Task task = getTaskById(taskId);
        if(updatedTaskFromUser.getTitle() != null ||
                updatedTaskFromUser.getDescription() != null ||
                updatedTaskFromUser.getStatus() != null){
            Optional.ofNullable(updatedTaskFromUser.getTitle()).ifPresent(task::setTitle);
            Optional.ofNullable(updatedTaskFromUser.getDescription()).ifPresent(task::setDescription);
            Optional.ofNullable(updatedTaskFromUser.getStatus()).ifPresent(task::setStatus);
            taskRepository.save(task);
        } else {
            throw new NoFieldsProvidedException("No fields provided for update");
        }
    }
}