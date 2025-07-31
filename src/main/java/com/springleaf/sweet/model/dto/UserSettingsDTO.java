package com.springleaf.sweet.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.LocalTime;

@Data
public class UserSettingsDTO {
    
    @Min(value = 0, message = "提醒时间不能小于0分钟")
    @Max(value = 1440, message = "提醒时间不能超过1440分钟")
    private Integer notificationBeforeMinutes;
    
    private LocalTime quietStartTime;
    
    private LocalTime quietEndTime;
    
    @Min(value = 1, message = "默认课程时长不能小于1分钟")
    @Max(value = 480, message = "默认课程时长不能超过480分钟")
    private Integer defaultCourseDuration;
    
    @Min(value = 0, message = "一周开始日必须在0-1之间")
    @Max(value = 1, message = "一周开始日必须在0-1之间")
    private Integer weekStartDay;
    
    private String timezone;
}