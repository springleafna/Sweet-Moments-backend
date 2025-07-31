package com.springleaf.sweet.service;

import cn.dev33.satoken.stp.StpUtil;
import com.springleaf.sweet.common.Result;
import com.springleaf.sweet.enums.ResultCodeEnum;
import com.springleaf.sweet.mapper.UserSettingsMapper;
import com.springleaf.sweet.model.dto.UserSettingsDTO;
import com.springleaf.sweet.model.entity.UserSettings;
import com.springleaf.sweet.utils.UUIDUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserSettingsService {
    
    private final UserSettingsMapper settingsMapper;
    
    public Result<UserSettings> getUserSettings() {
        String userId = StpUtil.getLoginIdAsString();
        UserSettings settings = settingsMapper.selectByUserId(userId);
        
        if (settings == null) {
            settings = createDefaultSettings(userId);
        }
        
        return Result.success(settings);
    }
    
    public Result<String> updateUserSettings(UserSettingsDTO settingsDTO) {
        String userId = StpUtil.getLoginIdAsString();
        UserSettings existingSettings = settingsMapper.selectByUserId(userId);
        
        if (existingSettings == null) {
            return createUserSettings(settingsDTO);
        }
        
        UserSettings settings = new UserSettings();
        settings.setUserId(userId);
        settings.setNotificationBeforeMinutes(settingsDTO.getNotificationBeforeMinutes());
        settings.setQuietStartTime(settingsDTO.getQuietStartTime());
        settings.setQuietEndTime(settingsDTO.getQuietEndTime());
        settings.setDefaultCourseDuration(settingsDTO.getDefaultCourseDuration());
        settings.setWeekStartDay(settingsDTO.getWeekStartDay());
        settings.setTimezone(settingsDTO.getTimezone());
        
        int result = settingsMapper.updateByUserId(settings);
        if (result > 0) {
            return Result.success("设置更新成功");
        } else {
            return Result.error(ResultCodeEnum.OPERATION_FAILED);
        }
    }
    
    private Result<String> createUserSettings(UserSettingsDTO settingsDTO) {
        String userId = StpUtil.getLoginIdAsString();
        
        UserSettings settings = new UserSettings();
        settings.setSettingId(UUIDUtils.generateUUID());
        settings.setUserId(userId);
        settings.setNotificationBeforeMinutes(settingsDTO.getNotificationBeforeMinutes() != null ? 
                settingsDTO.getNotificationBeforeMinutes() : 15);
        settings.setQuietStartTime(settingsDTO.getQuietStartTime());
        settings.setQuietEndTime(settingsDTO.getQuietEndTime());
        settings.setDefaultCourseDuration(settingsDTO.getDefaultCourseDuration() != null ? 
                settingsDTO.getDefaultCourseDuration() : 60);
        settings.setWeekStartDay(settingsDTO.getWeekStartDay() != null ? 
                settingsDTO.getWeekStartDay() : 1);
        settings.setTimezone(settingsDTO.getTimezone() != null ? 
                settingsDTO.getTimezone() : "Asia/Shanghai");
        settings.setCreatedAt(LocalDateTime.now());
        settings.setUpdatedAt(LocalDateTime.now());
        
        int result = settingsMapper.insert(settings);
        if (result > 0) {
            return Result.success("设置创建成功");
        } else {
            return Result.error(ResultCodeEnum.OPERATION_FAILED);
        }
    }
    
    private UserSettings createDefaultSettings(String userId) {
        UserSettings defaultSettings = new UserSettings();
        defaultSettings.setSettingId(UUIDUtils.generateUUID());
        defaultSettings.setUserId(userId);
        defaultSettings.setNotificationBeforeMinutes(15);
        defaultSettings.setDefaultCourseDuration(60);
        defaultSettings.setWeekStartDay(1);
        defaultSettings.setTimezone("Asia/Shanghai");
        defaultSettings.setCreatedAt(LocalDateTime.now());
        defaultSettings.setUpdatedAt(LocalDateTime.now());
        
        settingsMapper.insert(defaultSettings);
        return defaultSettings;
    }
    
    public Result<String> resetUserSettings() {
        String userId = StpUtil.getLoginIdAsString();
        
        int deleteResult = settingsMapper.deleteByUserId(userId);
        if (deleteResult > 0) {
            createDefaultSettings(userId);
            return Result.success("设置已重置为默认值");
        } else {
            return Result.error(ResultCodeEnum.OPERATION_FAILED);
        }
    }
}