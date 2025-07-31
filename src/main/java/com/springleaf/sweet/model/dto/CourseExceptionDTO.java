package com.springleaf.sweet.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CourseExceptionDTO {
    
    @NotBlank(message = "规则ID不能为空")
    private String ruleId;
    
    @NotNull(message = "原始日期不能为空")
    private LocalDate originalDate;
    
    @NotBlank(message = "异常类型不能为空")
    private String exceptionType;
    
    private LocalDateTime newStartTime;
    
    private LocalDateTime newEndTime;
    
    private String reason;
}