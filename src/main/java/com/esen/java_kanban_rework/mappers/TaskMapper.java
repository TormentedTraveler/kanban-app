package com.esen.java_kanban_rework.mappers;

import com.esen.java_kanban_rework.dto.TaskDTO;
import com.esen.java_kanban_rework.entity.Task;

import java.util.List;
import java.util.stream.Collectors;

public class TaskMapper {
    public static TaskDTO toDTO(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getBoard() != null ? task.getBoard().getId() : null // Assuming Board has an id field
        );
    }

    public static List<TaskDTO> toDTOList(List<Task> tasks) {
        return tasks.stream()
                .map(TaskMapper::toDTO)
                .collect(Collectors.toList());
    }
}
