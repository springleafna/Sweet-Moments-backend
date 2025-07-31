package com.springleaf.sweet.service;

import cn.dev33.satoken.stp.StpUtil;
import com.springleaf.sweet.common.Result;
import com.springleaf.sweet.enums.ResultCodeEnum;
import com.springleaf.sweet.enums.UserStatusEnum;
import com.springleaf.sweet.mapper.UserMapper;
import com.springleaf.sweet.model.dto.UserLoginDTO;
import com.springleaf.sweet.model.dto.UserRegisterDTO;
import com.springleaf.sweet.model.dto.UserUpdateDTO;
import com.springleaf.sweet.model.entity.User;
import com.springleaf.sweet.utils.PasswordUtils;
import com.springleaf.sweet.utils.UUIDUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserMapper userMapper;
    
    public Result<Map<String, Object>> login(UserLoginDTO loginDTO) {
        User user = userMapper.selectByEmail(loginDTO.getEmail());
        if (user == null) {
            return Result.error(ResultCodeEnum.USER_NOT_EXIST);
        }
        
        if (!PasswordUtils.verifyPassword(loginDTO.getPassword(), user.getPasswordHash())) {
            return Result.error(ResultCodeEnum.PASSWORD_ERROR);
        }
        
        if (!UserStatusEnum.NORMAL.getCode().equals(user.getStatus())) {
            return Result.error(ResultCodeEnum.USER_DISABLED);
        }
        
        StpUtil.login(user.getUserId());
        String token = StpUtil.getTokenValue();
        
        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("userInfo", user);
        
        return Result.success(result);
    }
    
    public Result<String> register(UserRegisterDTO registerDTO) {
        User existUser = userMapper.selectByEmail(registerDTO.getEmail());
        if (existUser != null) {
            return Result.error(ResultCodeEnum.USER_ALREADY_EXIST);
        }
        
        User user = new User();
        user.setUserId(UUIDUtils.generateUUID());
        user.setEmail(registerDTO.getEmail());
        user.setPasswordHash(PasswordUtils.encryptPassword(registerDTO.getPassword()));
        user.setNickname(registerDTO.getNickname());
        user.setStatus(UserStatusEnum.NORMAL.getCode());
        user.setNotificationEnabled(true);
        user.setTheme("light");
        user.setLanguage("zh-CN");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        
        int result = userMapper.insert(user);
        if (result > 0) {
            return Result.success("注册成功");
        } else {
            return Result.error(ResultCodeEnum.OPERATION_FAILED);
        }
    }
    
    public Result<String> logout() {
        StpUtil.logout();
        return Result.success("退出成功");
    }
    
    public Result<User> getUserInfo() {
        String userId = StpUtil.getLoginIdAsString();
        User user = userMapper.selectById(userId);
        if (user == null) {
            return Result.error(ResultCodeEnum.USER_NOT_EXIST);
        }
        return Result.success(user);
    }
    
    public Result<String> updateUserInfo(UserUpdateDTO updateDTO) {
        String userId = StpUtil.getLoginIdAsString();
        
        User user = new User();
        user.setUserId(userId);
        user.setNickname(updateDTO.getNickname());
        user.setAvatarUrl(updateDTO.getAvatarUrl());
        user.setTheme(updateDTO.getTheme());
        user.setLanguage(updateDTO.getLanguage());
        user.setNotificationEnabled(updateDTO.getNotificationEnabled());
        
        int result = userMapper.updateById(user);
        if (result > 0) {
            return Result.success("更新成功");
        } else {
            return Result.error(ResultCodeEnum.OPERATION_FAILED);
        }
    }
    
    public Result<String> bindPartner(String partnerEmail) {
        String userId = StpUtil.getLoginIdAsString();
        
        User partner = userMapper.selectByEmail(partnerEmail);
        if (partner == null) {
            return Result.error(ResultCodeEnum.USER_NOT_EXIST);
        }
        
        if (partner.getUserId().equals(userId)) {
            return Result.error("不能绑定自己");
        }
        
        int result = userMapper.updatePartnerId(userId, partner.getUserId());
        if (result > 0) {
            return Result.success("绑定成功");
        } else {
            return Result.error(ResultCodeEnum.OPERATION_FAILED);
        }
    }
}