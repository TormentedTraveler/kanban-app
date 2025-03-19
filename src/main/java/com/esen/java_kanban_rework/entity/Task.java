package com.esen.java_kanban_rework.entity;

import com.esen.java_kanban_rework.utils.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String color;
    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.TO_DO;

    @ManyToOne()
    @JoinColumn(name = "board_id")
    private Board board;
}