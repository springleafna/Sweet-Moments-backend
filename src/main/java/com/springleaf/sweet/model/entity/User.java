package com.springleaf.sweet.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userId;
    private String email;
    private String passwordHash;
    private String nickname;
    private String avatarUrl;
    private String partnerId;
    private Integer status;
    private Boolean notificationEnabled;
    private String theme;
    private String language;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}