package com.springleaf.sweet.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseException {
    private String exceptionId;
    private String ruleId;
    private LocalDate originalDate;
    private String exceptionType;
    private LocalDateTime newStartTime;
    private LocalDateTime newEndTime;
    private String reason;
    private LocalDateTime createdAt;
}