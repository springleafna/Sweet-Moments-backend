package com.springleaf.sweet.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CourseVO {
    private String courseId;
    private String userId;
    private String studentName;
    private String courseType;
    private String courseTypeDesc;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String note;
    private Integer status;
    private String statusDesc;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}