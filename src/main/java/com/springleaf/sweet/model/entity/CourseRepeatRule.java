package com.springleaf.sweet.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRepeatRule {
    private String ruleId;
    private String courseId;
    private String repeatType;
    private Integer repeatInterval;
    private LocalDate endDate;
    private String weekdays;
    private String monthlyType;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}