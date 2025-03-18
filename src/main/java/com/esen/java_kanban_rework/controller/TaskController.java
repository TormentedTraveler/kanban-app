package com.esen.java_kanban_rework.controller;

import com.esen.java_kanban_rework.dto.TaskDTO;
import com.esen.java_kanban_rework.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/")
    public ResponseEntity<Void> createTask(@RequestBody TaskDTO newTask) {
        taskService.createTask(newTask);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO> getTaskByIdForUser(@PathVariable("taskId") Long taskId) {
        return ResponseEntity.ok(taskService.getTaskByIdForUser(taskId));
    }

    @PatchMapping("/{taskId}")
    public ResponseEntity<Void> updateTask(@PathVariable("taskId") Long taskId, @RequestBody TaskDTO updatedTask) {
        taskService.updateTask(taskId, updatedTask);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}