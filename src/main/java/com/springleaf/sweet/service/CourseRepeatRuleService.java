package com.springleaf.sweet.service;

import com.springleaf.sweet.common.Result;
import com.springleaf.sweet.model.dto.CourseRepeatRuleDTO;
import com.springleaf.sweet.model.entity.CourseRepeatRule;

import java.util.List;

/**
 * 课程重复规则服务接口
 * 提供课程重复规则相关的业务操作功能
 *
 * @author Sweet Moments Team
 */
public interface CourseRepeatRuleService {
    
    /**
     * 创建重复规则
     *
     * @param ruleDTO 重复规则信息
     * @return 创建结果
     */
    Result<String> createRule(CourseRepeatRuleDTO ruleDTO);
    
    /**
     * 根据课程ID获取重复规则列表
     *
     * @param courseId 课程ID
     * @return 重复规则列表
     */
    Result<List<CourseRepeatRule>> getRulesByCourseId(String courseId);
    
    /**
     * 根据规则ID获取重复规则详情
     *
     * @param ruleId 规则ID
     * @return 重复规则详情
     */
    Result<CourseRepeatRule> getRuleById(String ruleId);
    
    /**
     * 更新重复规则
     *
     * @param ruleId 规则ID
     * @param ruleDTO 更新信息
     * @return 更新结果
     */
    Result<String> updateRule(String ruleId, CourseRepeatRuleDTO ruleDTO);
    
    /**
     * 停用重复规则
     *
     * @param ruleId 规则ID
     * @return 停用结果
     */
    Result<String> disableRule(String ruleId);
    
    /**
     * 启用重复规则
     *
     * @param ruleId 规则ID
     * @return 启用结果
     */
    Result<String> enableRule(String ruleId);
    
    /**
     * 删除重复规则
     *
     * @param ruleId 规则ID
     * @return 删除结果
     */
    Result<String> deleteRule(String ruleId);
}