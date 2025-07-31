package com.springleaf.sweet.model.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class UserUpdateDTO {
    
    @Size(max = 50, message = "昵称长度不能超过50位")
    private String nickname;
    
    @Size(max = 500, message = "头像URL长度不能超过500位")
    private String avatarUrl;
    
    private String theme;
    
    private String language;
    
    private Boolean notificationEnabled;
}