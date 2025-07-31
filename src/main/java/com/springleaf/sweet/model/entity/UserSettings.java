package com.springleaf.sweet.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSettings {
    private String settingId;
    private String userId;
    private Integer notificationBeforeMinutes;
    private LocalTime quietStartTime;
    private LocalTime quietEndTime;
    private Integer defaultCourseDuration;
    private Integer weekStartDay;
    private String timezone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}