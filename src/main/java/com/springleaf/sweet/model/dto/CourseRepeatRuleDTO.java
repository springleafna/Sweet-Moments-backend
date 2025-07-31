package com.springleaf.sweet.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CourseRepeatRuleDTO {
    
    @NotBlank(message = "课程ID不能为空")
    private String courseId;
    
    @NotBlank(message = "重复类型不能为空")
    private String repeatType;
    
    @NotNull(message = "重复间隔不能为空")
    private Integer repeatInterval;
    
    private LocalDate endDate;
    
    private String weekdays;
    
    private String monthlyType;
}