package com.springleaf.sweet.service;

import com.springleaf.sweet.common.Result;
import com.springleaf.sweet.model.dto.UserSettingsDTO;
import com.springleaf.sweet.model.entity.UserSettings;

/**
 * 用户设置服务接口
 * 提供用户设置相关的业务操作功能
 *
 * @author Sweet Moments Team
 */
public interface UserSettingsService {
    
    /**
     * 获取用户设置
     *
     * @return 用户设置信息
     */
    Result<UserSettings> getUserSettings();
    
    /**
     * 更新用户设置
     *
     * @param settingsDTO 设置信息
     * @return 更新结果
     */
    Result<String> updateUserSettings(UserSettingsDTO settingsDTO);
    
    /**
     * 重置用户设置为默认值
     *
     * @return 重置结果
     */
    Result<String> resetUserSettings();
}