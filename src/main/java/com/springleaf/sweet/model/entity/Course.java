package com.springleaf.sweet.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private String courseId;
    private String userId;
    private String studentName;
    private String courseType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String note;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}