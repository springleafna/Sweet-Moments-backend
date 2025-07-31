package com.springleaf.sweet.mapper;

import com.springleaf.sweet.model.entity.Course;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface CourseMapper {
    
    @Insert("INSERT INTO courses(course_id, user_id, student_name, course_type, start_time, end_time, note, status) " +
            "VALUES(#{courseId}, #{userId}, #{studentName}, #{courseType}, #{startTime}, #{endTime}, #{note}, #{status})")
    int insert(Course course);
    
    @Select("SELECT * FROM courses WHERE course_id = #{courseId}")
    Course selectById(String courseId);
    
    @Select("SELECT * FROM courses WHERE user_id = #{userId} AND status != 2 ORDER BY start_time")
    List<Course> selectByUserId(String userId);
    
    @Select("SELECT * FROM courses WHERE user_id = #{userId} AND start_time >= #{startTime} AND end_time <= #{endTime} AND status != 2 ORDER BY start_time")
    List<Course> selectByUserIdAndTimeRange(@Param("userId") String userId, 
                                           @Param("startTime") LocalDateTime startTime, 
                                           @Param("endTime") LocalDateTime endTime);
    
    @Select("SELECT * FROM courses WHERE user_id = #{userId} AND course_type = #{courseType} AND status != 2 ORDER BY start_time")
    List<Course> selectByUserIdAndType(@Param("userId") String userId, @Param("courseType") String courseType);
    
    @Update("UPDATE courses SET student_name = #{studentName}, course_type = #{courseType}, " +
            "start_time = #{startTime}, end_time = #{endTime}, note = #{note} WHERE course_id = #{courseId}")
    int updateById(Course course);
    
    @Update("UPDATE courses SET status = #{status} WHERE course_id = #{courseId}")
    int updateStatus(@Param("courseId") String courseId, @Param("status") Integer status);
    
    @Delete("DELETE FROM courses WHERE course_id = #{courseId}")
    int deleteById(String courseId);
}