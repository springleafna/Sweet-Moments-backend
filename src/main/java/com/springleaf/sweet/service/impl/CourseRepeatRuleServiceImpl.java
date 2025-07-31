package com.springleaf.sweet.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.springleaf.sweet.common.Result;
import com.springleaf.sweet.enums.RepeatTypeEnum;
import com.springleaf.sweet.enums.ResultCodeEnum;
import com.springleaf.sweet.mapper.CourseMapper;
import com.springleaf.sweet.mapper.CourseRepeatRuleMapper;
import com.springleaf.sweet.model.dto.CourseRepeatRuleDTO;
import com.springleaf.sweet.model.entity.Course;
import com.springleaf.sweet.model.entity.CourseRepeatRule;
import com.springleaf.sweet.service.CourseRepeatRuleService;
import com.springleaf.sweet.utils.UUIDUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 课程重复规则服务实现类
 *
 * @author Sweet Moments Team
 */
@Service
@RequiredArgsConstructor
public class CourseRepeatRuleServiceImpl implements CourseRepeatRuleService {
    
    private final CourseRepeatRuleMapper ruleMapper;
    private final CourseMapper courseMapper;
    
    @Override
    public Result<String> createRule(CourseRepeatRuleDTO ruleDTO) {
        String userId = StpUtil.getLoginIdAsString();
        
        Course course = courseMapper.selectById(ruleDTO.getCourseId());
        if (course == null) {
            return Result.error(ResultCodeEnum.COURSE_NOT_EXIST);
        }
        
        if (!course.getUserId().equals(userId)) {
            return Result.error(ResultCodeEnum.FORBIDDEN);
        }
        
        if (RepeatTypeEnum.getByCode(ruleDTO.getRepeatType()) == null) {
            return Result.error(ResultCodeEnum.INVALID_REPEAT_TYPE);
        }
        
        CourseRepeatRule rule = new CourseRepeatRule();
        rule.setRuleId(UUIDUtils.generateUUID());
        rule.setCourseId(ruleDTO.getCourseId());
        rule.setRepeatType(ruleDTO.getRepeatType());
        rule.setRepeatInterval(ruleDTO.getRepeatInterval());
        rule.setEndDate(ruleDTO.getEndDate());
        rule.setWeekdays(ruleDTO.getWeekdays());
        rule.setMonthlyType(ruleDTO.getMonthlyType());
        rule.setStatus(1);
        rule.setCreatedAt(LocalDateTime.now());
        rule.setUpdatedAt(LocalDateTime.now());
        
        int result = ruleMapper.insert(rule);
        if (result > 0) {
            return Result.success("重复规则创建成功");
        } else {
            return Result.error(ResultCodeEnum.OPERATION_FAILED);
        }
    }
    
    @Override
    public Result<List<CourseRepeatRule>> getRulesByCourseId(String courseId) {
        String userId = StpUtil.getLoginIdAsString();
        
        Course course = courseMapper.selectById(courseId);
        if (course == null) {
            return Result.error(ResultCodeEnum.COURSE_NOT_EXIST);
        }
        
        if (!course.getUserId().equals(userId)) {
            return Result.error(ResultCodeEnum.FORBIDDEN);
        }
        
        List<CourseRepeatRule> rules = ruleMapper.selectByCourseId(courseId);
        return Result.success(rules);
    }
    
    @Override
    public Result<CourseRepeatRule> getRuleById(String ruleId) {
        CourseRepeatRule rule = ruleMapper.selectById(ruleId);
        if (rule == null) {
            return Result.error(ResultCodeEnum.RULE_NOT_EXIST);
        }
        
        String userId = StpUtil.getLoginIdAsString();
        Course course = courseMapper.selectById(rule.getCourseId());
        if (course == null || !course.getUserId().equals(userId)) {
            return Result.error(ResultCodeEnum.FORBIDDEN);
        }
        
        return Result.success(rule);
    }
    
    @Override
    public Result<String> updateRule(String ruleId, CourseRepeatRuleDTO ruleDTO) {
        CourseRepeatRule rule = ruleMapper.selectById(ruleId);
        if (rule == null) {
            return Result.error(ResultCodeEnum.RULE_NOT_EXIST);
        }
        
        String userId = StpUtil.getLoginIdAsString();
        Course course = courseMapper.selectById(rule.getCourseId());
        if (course == null || !course.getUserId().equals(userId)) {
            return Result.error(ResultCodeEnum.FORBIDDEN);
        }
        
        if (RepeatTypeEnum.getByCode(ruleDTO.getRepeatType()) == null) {
            return Result.error(ResultCodeEnum.INVALID_REPEAT_TYPE);
        }
        
        rule.setRepeatType(ruleDTO.getRepeatType());
        rule.setRepeatInterval(ruleDTO.getRepeatInterval());
        rule.setEndDate(ruleDTO.getEndDate());
        rule.setWeekdays(ruleDTO.getWeekdays());
        rule.setMonthlyType(ruleDTO.getMonthlyType());
        rule.setUpdatedAt(LocalDateTime.now());
        
        int result = ruleMapper.updateById(rule);
        if (result > 0) {
            return Result.success("重复规则更新成功");
        } else {
            return Result.error(ResultCodeEnum.OPERATION_FAILED);
        }
    }
    
    @Override
    public Result<String> disableRule(String ruleId) {
        CourseRepeatRule rule = ruleMapper.selectById(ruleId);
        if (rule == null) {
            return Result.error(ResultCodeEnum.RULE_NOT_EXIST);
        }
        
        String userId = StpUtil.getLoginIdAsString();
        Course course = courseMapper.selectById(rule.getCourseId());
        if (course == null || !course.getUserId().equals(userId)) {
            return Result.error(ResultCodeEnum.FORBIDDEN);
        }
        
        int result = ruleMapper.updateStatus(ruleId, 0);
        if (result > 0) {
            return Result.success("重复规则已停用");
        } else {
            return Result.error(ResultCodeEnum.OPERATION_FAILED);
        }
    }
    
    @Override
    public Result<String> enableRule(String ruleId) {
        CourseRepeatRule rule = ruleMapper.selectById(ruleId);
        if (rule == null) {
            return Result.error(ResultCodeEnum.RULE_NOT_EXIST);
        }
        
        String userId = StpUtil.getLoginIdAsString();
        Course course = courseMapper.selectById(rule.getCourseId());
        if (course == null || !course.getUserId().equals(userId)) {
            return Result.error(ResultCodeEnum.FORBIDDEN);
        }
        
        int result = ruleMapper.updateStatus(ruleId, 1);
        if (result > 0) {
            return Result.success("重复规则已启用");
        } else {
            return Result.error(ResultCodeEnum.OPERATION_FAILED);
        }
    }
    
    @Override
    public Result<String> deleteRule(String ruleId) {
        CourseRepeatRule rule = ruleMapper.selectById(ruleId);
        if (rule == null) {
            return Result.error(ResultCodeEnum.RULE_NOT_EXIST);
        }
        
        String userId = StpUtil.getLoginIdAsString();
        Course course = courseMapper.selectById(rule.getCourseId());
        if (course == null || !course.getUserId().equals(userId)) {
            return Result.error(ResultCodeEnum.FORBIDDEN);
        }
        
        int result = ruleMapper.deleteById(ruleId);
        if (result > 0) {
            return Result.success("重复规则已删除");
        } else {
            return Result.error(ResultCodeEnum.OPERATION_FAILED);
        }
    }
}