package com.springleaf.sweet.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.springleaf.sweet.common.Result;
import com.springleaf.sweet.enums.CourseStatusEnum;
import com.springleaf.sweet.enums.CourseTypeEnum;
import com.springleaf.sweet.enums.ResultCodeEnum;
import com.springleaf.sweet.mapper.CourseMapper;
import com.springleaf.sweet.model.dto.CourseCreateDTO;
import com.springleaf.sweet.model.entity.Course;
import com.springleaf.sweet.service.CourseService;
import com.springleaf.sweet.utils.DateTimeUtils;
import com.springleaf.sweet.utils.UUIDUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 课程服务实现类
 *
 * @author Sweet Moments Team
 */
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    
    private final CourseMapper courseMapper;
    
    @Override
    public Result<String> createCourse(CourseCreateDTO createDTO) {
        String userId = StpUtil.getLoginIdAsString();
        
        if (CourseTypeEnum.getByCode(createDTO.getCourseType()) == null) {
            return Result.error(ResultCodeEnum.INVALID_COURSE_TYPE);
        }
        
        if (createDTO.getStartTime().isAfter(createDTO.getEndTime())) {
            return Result.error(ResultCodeEnum.INVALID_TIME_RANGE);
        }
        
        List<Course> existingCourses = courseMapper.selectByUserIdAndTimeRange(
                userId, createDTO.getStartTime(), createDTO.getEndTime());
        
        for (Course existingCourse : existingCourses) {
            if (DateTimeUtils.isTimeConflict(
                    createDTO.getStartTime(), createDTO.getEndTime(),
                    existingCourse.getStartTime(), existingCourse.getEndTime())) {
                return Result.error(ResultCodeEnum.COURSE_TIME_CONFLICT);
            }
        }
        
        Course course = new Course();
        course.setCourseId(UUIDUtils.generateUUID());
        course.setUserId(userId);
        course.setStudentName(createDTO.getStudentName());
        course.setCourseType(createDTO.getCourseType());
        course.setStartTime(createDTO.getStartTime());
        course.setEndTime(createDTO.getEndTime());
        course.setNote(createDTO.getNote());
        course.setStatus(CourseStatusEnum.NORMAL.getCode());
        course.setCreatedAt(LocalDateTime.now());
        course.setUpdatedAt(LocalDateTime.now());
        
        int result = courseMapper.insert(course);
        if (result > 0) {
            return Result.success("课程创建成功");
        } else {
            return Result.error(ResultCodeEnum.OPERATION_FAILED);
        }
    }
    
    @Override
    public Result<List<Course>> getUserCourses() {
        String userId = StpUtil.getLoginIdAsString();
        List<Course> courses = courseMapper.selectByUserId(userId);
        return Result.success(courses);
    }
    
    @Override
    public Result<List<Course>> getUserCoursesByTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        String userId = StpUtil.getLoginIdAsString();
        List<Course> courses = courseMapper.selectByUserIdAndTimeRange(userId, startTime, endTime);
        return Result.success(courses);
    }
    
    @Override
    public Result<List<Course>> getUserCoursesByType(String courseType) {
        String userId = StpUtil.getLoginIdAsString();
        
        if (CourseTypeEnum.getByCode(courseType) == null) {
            return Result.error(ResultCodeEnum.INVALID_COURSE_TYPE);
        }
        
        List<Course> courses = courseMapper.selectByUserIdAndType(userId, courseType);
        return Result.success(courses);
    }
    
    @Override
    public Result<Course> getCourseById(String courseId) {
        Course course = courseMapper.selectById(courseId);
        if (course == null) {
            return Result.error(ResultCodeEnum.COURSE_NOT_EXIST);
        }
        
        String userId = StpUtil.getLoginIdAsString();
        if (!course.getUserId().equals(userId)) {
            return Result.error(ResultCodeEnum.FORBIDDEN);
        }
        
        return Result.success(course);
    }
    
    @Override
    public Result<String> updateCourse(String courseId, CourseCreateDTO updateDTO) {
        Course course = courseMapper.selectById(courseId);
        if (course == null) {
            return Result.error(ResultCodeEnum.COURSE_NOT_EXIST);
        }
        
        String userId = StpUtil.getLoginIdAsString();
        if (!course.getUserId().equals(userId)) {
            return Result.error(ResultCodeEnum.FORBIDDEN);
        }
        
        if (CourseTypeEnum.getByCode(updateDTO.getCourseType()) == null) {
            return Result.error(ResultCodeEnum.INVALID_COURSE_TYPE);
        }
        
        if (updateDTO.getStartTime().isAfter(updateDTO.getEndTime())) {
            return Result.error(ResultCodeEnum.INVALID_TIME_RANGE);
        }
        
        course.setStudentName(updateDTO.getStudentName());
        course.setCourseType(updateDTO.getCourseType());
        course.setStartTime(updateDTO.getStartTime());
        course.setEndTime(updateDTO.getEndTime());
        course.setNote(updateDTO.getNote());
        course.setUpdatedAt(LocalDateTime.now());
        
        int result = courseMapper.updateById(course);
        if (result > 0) {
            return Result.success("课程更新成功");
        } else {
            return Result.error(ResultCodeEnum.OPERATION_FAILED);
        }
    }
    
    @Override
    public Result<String> cancelCourse(String courseId) {
        Course course = courseMapper.selectById(courseId);
        if (course == null) {
            return Result.error(ResultCodeEnum.COURSE_NOT_EXIST);
        }
        
        String userId = StpUtil.getLoginIdAsString();
        if (!course.getUserId().equals(userId)) {
            return Result.error(ResultCodeEnum.FORBIDDEN);
        }
        
        int result = courseMapper.updateStatus(courseId, CourseStatusEnum.CANCELLED.getCode());
        if (result > 0) {
            return Result.success("课程已取消");
        } else {
            return Result.error(ResultCodeEnum.OPERATION_FAILED);
        }
    }
    
    @Override
    public Result<String> completeCourse(String courseId) {
        Course course = courseMapper.selectById(courseId);
        if (course == null) {
            return Result.error(ResultCodeEnum.COURSE_NOT_EXIST);
        }
        
        String userId = StpUtil.getLoginIdAsString();
        if (!course.getUserId().equals(userId)) {
            return Result.error(ResultCodeEnum.FORBIDDEN);
        }
        
        int result = courseMapper.updateStatus(courseId, CourseStatusEnum.COMPLETED.getCode());
        if (result > 0) {
            return Result.success("课程已完成");
        } else {
            return Result.error(ResultCodeEnum.OPERATION_FAILED);
        }
    }
    
    @Override
    public Result<String> deleteCourse(String courseId) {
        Course course = courseMapper.selectById(courseId);
        if (course == null) {
            return Result.error(ResultCodeEnum.COURSE_NOT_EXIST);
        }
        
        String userId = StpUtil.getLoginIdAsString();
        if (!course.getUserId().equals(userId)) {
            return Result.error(ResultCodeEnum.FORBIDDEN);
        }
        
        int result = courseMapper.deleteById(courseId);
        if (result > 0) {
            return Result.success("课程已删除");
        } else {
            return Result.error(ResultCodeEnum.OPERATION_FAILED);
        }
    }
}