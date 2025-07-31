package com.springleaf.sweet.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CourseCreateDTO {
    
    @NotBlank(message = "学生姓名不能为空")
    private String studentName;
    
    @NotBlank(message = "课程类型不能为空")
    private String courseType;
    
    @NotNull(message = "开始时间不能为空")
    private LocalDateTime startTime;
    
    @NotNull(message = "结束时间不能为空")
    private LocalDateTime endTime;
    
    private String note;
}