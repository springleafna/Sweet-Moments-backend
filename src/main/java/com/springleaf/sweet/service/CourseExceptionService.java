package com.springleaf.sweet.service;

import com.springleaf.sweet.common.Result;
import com.springleaf.sweet.model.dto.CourseExceptionDTO;
import com.springleaf.sweet.model.entity.CourseException;

import java.util.List;

/**
 * 课程异常服务接口
 * 提供课程异常记录相关的业务操作功能
 *
 * @author Sweet Moments Team
 */
public interface CourseExceptionService {
    
    /**
     * 创建课程异常记录
     *
     * @param exceptionDTO 异常信息
     * @return 创建结果
     */
    Result<String> createException(CourseExceptionDTO exceptionDTO);
    
    /**
     * 根据规则ID获取异常记录列表
     *
     * @param ruleId 规则ID
     * @return 异常记录列表
     */
    Result<List<CourseException>> getExceptionsByRuleId(String ruleId);
    
    /**
     * 根据异常ID获取异常记录详情
     *
     * @param exceptionId 异常ID
     * @return 异常记录详情
     */
    Result<CourseException> getExceptionById(String exceptionId);
    
    /**
     * 更新课程异常记录
     *
     * @param exceptionId 异常ID
     * @param exceptionDTO 更新信息
     * @return 更新结果
     */
    Result<String> updateException(String exceptionId, CourseExceptionDTO exceptionDTO);
    
    /**
     * 删除课程异常记录
     *
     * @param exceptionId 异常ID
     * @return 删除结果
     */
    Result<String> deleteException(String exceptionId);
}