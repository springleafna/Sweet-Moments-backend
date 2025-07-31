package com.springleaf.sweet.service;

import com.springleaf.sweet.common.Result;
import com.springleaf.sweet.model.dto.UserLoginDTO;
import com.springleaf.sweet.model.dto.UserRegisterDTO;
import com.springleaf.sweet.model.dto.UserUpdateDTO;
import com.springleaf.sweet.model.entity.User;

import java.util.Map;

/**
 * 用户服务接口
 * 提供用户相关的业务操作功能
 *
 * @author Sweet Moments Team
 */
public interface UserService {
    
    /**
     * 用户登录
     *
     * @param loginDTO 登录信息
     * @return 登录结果，包含token和用户信息
     */
    Result<Map<String, Object>> login(UserLoginDTO loginDTO);
    
    /**
     * 用户注册
     *
     * @param registerDTO 注册信息
     * @return 注册结果
     */
    Result<String> register(UserRegisterDTO registerDTO);
    
    /**
     * 用户登出
     *
     * @return 登出结果
     */
    Result<String> logout();
    
    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    Result<User> getUserInfo();
    
    /**
     * 更新用户信息
     *
     * @param updateDTO 更新信息
     * @return 更新结果
     */
    Result<String> updateUserInfo(UserUpdateDTO updateDTO);
    
    /**
     * 绑定伴侣
     *
     * @param partnerEmail 伴侣邮箱
     * @return 绑定结果
     */
    Result<String> bindPartner(String partnerEmail);
}