package com.springleaf.sweet.service;

import cn.dev33.satoken.stp.StpUtil;
import com.springleaf.sweet.common.Result;
import com.springleaf.sweet.enums.ExceptionTypeEnum;
import com.springleaf.sweet.enums.ResultCodeEnum;
import com.springleaf.sweet.mapper.CourseExceptionMapper;
import com.springleaf.sweet.mapper.CourseMapper;
import com.springleaf.sweet.mapper.CourseRepeatRuleMapper;
import com.springleaf.sweet.model.dto.CourseExceptionDTO;
import com.springleaf.sweet.model.entity.Course;
import com.springleaf.sweet.model.entity.CourseException;
import com.springleaf.sweet.model.entity.CourseRepeatRule;
import com.springleaf.sweet.utils.UUIDUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseExceptionService {
    
    private final CourseExceptionMapper exceptionMapper;
    private final CourseRepeatRuleMapper ruleMapper;
    private final CourseMapper courseMapper;
    
    public Result<String> createException(CourseExceptionDTO exceptionDTO) {
        String userId = StpUtil.getLoginIdAsString();
        
        CourseRepeatRule rule = ruleMapper.selectById(exceptionDTO.getRuleId());
        if (rule == null) {
            return Result.error(ResultCodeEnum.RULE_NOT_EXIST);
        }
        
        Course course = courseMapper.selectById(rule.getCourseId());
        if (course == null || !course.getUserId().equals(userId)) {
            return Result.error(ResultCodeEnum.FORBIDDEN);
        }
        
        if (ExceptionTypeEnum.getByCode(exceptionDTO.getExceptionType()) == null) {
            return Result.error(ResultCodeEnum.PARAM_ERROR);
        }
        
        CourseException existingException = exceptionMapper.selectByRuleIdAndDate(
                exceptionDTO.getRuleId(), exceptionDTO.getOriginalDate());
        if (existingException != null) {
            return Result.error("该日期已存在课程异常记录");
        }
        
        if ("reschedule".equals(exceptionDTO.getExceptionType())) {
            if (exceptionDTO.getNewStartTime() == null || exceptionDTO.getNewEndTime() == null) {
                return Result.error("改期类型异常必须提供新的开始和结束时间");
            }
            if (exceptionDTO.getNewStartTime().isAfter(exceptionDTO.getNewEndTime())) {
                return Result.error(ResultCodeEnum.INVALID_TIME_RANGE);
            }
        }
        
        CourseException exception = new CourseException();
        exception.setExceptionId(UUIDUtils.generateUUID());
        exception.setRuleId(exceptionDTO.getRuleId());
        exception.setOriginalDate(exceptionDTO.getOriginalDate());
        exception.setExceptionType(exceptionDTO.getExceptionType());
        exception.setNewStartTime(exceptionDTO.getNewStartTime());
        exception.setNewEndTime(exceptionDTO.getNewEndTime());
        exception.setReason(exceptionDTO.getReason());
        exception.setCreatedAt(LocalDateTime.now());
        
        int result = exceptionMapper.insert(exception);
        if (result > 0) {
            return Result.success("课程异常记录创建成功");
        } else {
            return Result.error(ResultCodeEnum.OPERATION_FAILED);
        }
    }
    
    public Result<List<CourseException>> getExceptionsByRuleId(String ruleId) {
        String userId = StpUtil.getLoginIdAsString();
        
        CourseRepeatRule rule = ruleMapper.selectById(ruleId);
        if (rule == null) {
            return Result.error(ResultCodeEnum.RULE_NOT_EXIST);
        }
        
        Course course = courseMapper.selectById(rule.getCourseId());
        if (course == null || !course.getUserId().equals(userId)) {
            return Result.error(ResultCodeEnum.FORBIDDEN);
        }
        
        List<CourseException> exceptions = exceptionMapper.selectByRuleId(ruleId);
        return Result.success(exceptions);
    }
    
    public Result<CourseException> getExceptionById(String exceptionId) {
        CourseException exception = exceptionMapper.selectById(exceptionId);
        if (exception == null) {
            return Result.error("课程异常记录不存在");
        }
        
        String userId = StpUtil.getLoginIdAsString();
        CourseRepeatRule rule = ruleMapper.selectById(exception.getRuleId());
        if (rule == null) {
            return Result.error(ResultCodeEnum.RULE_NOT_EXIST);
        }
        
        Course course = courseMapper.selectById(rule.getCourseId());
        if (course == null || !course.getUserId().equals(userId)) {
            return Result.error(ResultCodeEnum.FORBIDDEN);
        }
        
        return Result.success(exception);
    }
    
    public Result<String> updateException(String exceptionId, CourseExceptionDTO exceptionDTO) {
        CourseException exception = exceptionMapper.selectById(exceptionId);
        if (exception == null) {
            return Result.error("课程异常记录不存在");
        }
        
        String userId = StpUtil.getLoginIdAsString();
        CourseRepeatRule rule = ruleMapper.selectById(exception.getRuleId());
        if (rule == null) {
            return Result.error(ResultCodeEnum.RULE_NOT_EXIST);
        }
        
        Course course = courseMapper.selectById(rule.getCourseId());
        if (course == null || !course.getUserId().equals(userId)) {
            return Result.error(ResultCodeEnum.FORBIDDEN);
        }
        
        if (ExceptionTypeEnum.getByCode(exceptionDTO.getExceptionType()) == null) {
            return Result.error(ResultCodeEnum.PARAM_ERROR);
        }
        
        if ("reschedule".equals(exceptionDTO.getExceptionType())) {
            if (exceptionDTO.getNewStartTime() == null || exceptionDTO.getNewEndTime() == null) {
                return Result.error("改期类型异常必须提供新的开始和结束时间");
            }
            if (exceptionDTO.getNewStartTime().isAfter(exceptionDTO.getNewEndTime())) {
                return Result.error(ResultCodeEnum.INVALID_TIME_RANGE);
            }
        }
        
        exception.setExceptionType(exceptionDTO.getExceptionType());
        exception.setNewStartTime(exceptionDTO.getNewStartTime());
        exception.setNewEndTime(exceptionDTO.getNewEndTime());
        exception.setReason(exceptionDTO.getReason());
        
        int result = exceptionMapper.updateById(exception);
        if (result > 0) {
            return Result.success("课程异常记录更新成功");
        } else {
            return Result.error(ResultCodeEnum.OPERATION_FAILED);
        }
    }
    
    public Result<String> deleteException(String exceptionId) {
        CourseException exception = exceptionMapper.selectById(exceptionId);
        if (exception == null) {
            return Result.error("课程异常记录不存在");
        }
        
        String userId = StpUtil.getLoginIdAsString();
        CourseRepeatRule rule = ruleMapper.selectById(exception.getRuleId());
        if (rule == null) {
            return Result.error(ResultCodeEnum.RULE_NOT_EXIST);
        }
        
        Course course = courseMapper.selectById(rule.getCourseId());
        if (course == null || !course.getUserId().equals(userId)) {
            return Result.error(ResultCodeEnum.FORBIDDEN);
        }
        
        int result = exceptionMapper.deleteById(exceptionId);
        if (result > 0) {
            return Result.success("课程异常记录已删除");
        } else {
            return Result.error(ResultCodeEnum.OPERATION_FAILED);
        }
    }
}