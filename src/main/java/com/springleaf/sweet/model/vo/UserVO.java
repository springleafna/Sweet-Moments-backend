package com.springleaf.sweet.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserVO {
    private String userId;
    private String email;
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