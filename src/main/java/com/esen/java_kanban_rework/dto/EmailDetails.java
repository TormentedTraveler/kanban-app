package com.esen.java_kanban_rework.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDetails {
    private String to;
    private String subject;
    private String message;
}
