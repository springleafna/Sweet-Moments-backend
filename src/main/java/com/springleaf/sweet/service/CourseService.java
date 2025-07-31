package com.springleaf.sweet.service;

import com.springleaf.sweet.common.Result;
import com.springleaf.sweet.model.dto.CourseCreateDTO;
import com.springleaf.sweet.model.entity.Course;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 课程服务接口
 * 提供课程相关的业务操作功能
 *
 * @author Sweet Moments Team
 */
public interface CourseService {
    
    /**
     * 创建课程
     *
     * @param createDTO 课程创建信息
     * @return 创建结果
     */
    Result<String> createCourse(CourseCreateDTO createDTO);
    
    /**
     * 获取用户的所有课程
     *
     * @return 课程列表
     */
    Result<List<Course>> getUserCourses();
    
    /**
     * 根据时间范围获取用户课程
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 课程列表
     */
    Result<List<Course>> getUserCoursesByTimeRange(LocalDateTime startTime, LocalDateTime endTime);
    
    /**
     * 根据课程类型获取用户课程
     *
     * @param courseType 课程类型
     * @return 课程列表
     */
    Result<List<Course>> getUserCoursesByType(String courseType);
    
    /**
     * 根据ID获取课程详情
     *
     * @param courseId 课程ID
     * @return 课程详情
     */
    Result<Course> getCourseById(String courseId);
    
    /**
     * 更新课程信息
     *
     * @param courseId 课程ID
     * @param updateDTO 更新信息
     * @return 更新结果
     */
    Result<String> updateCourse(String courseId, CourseCreateDTO updateDTO);
    
    /**
     * 取消课程
     *
     * @param courseId 课程ID
     * @return 取消结果
     */
    Result<String> cancelCourse(String courseId);
    
    /**
     * 完成课程
     *
     * @param courseId 课程ID
     * @return 完成结果
     */
    Result<String> completeCourse(String courseId);
    
    /**
     * 删除课程
     *
     * @param courseId 课程ID
     * @return 删除结果
     */
    Result<String> deleteCourse(String courseId);
}