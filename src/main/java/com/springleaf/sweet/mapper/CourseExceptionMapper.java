package com.springleaf.sweet.mapper;

import com.springleaf.sweet.model.entity.CourseException;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface CourseExceptionMapper {
    
    @Insert("INSERT INTO course_exceptions(exception_id, rule_id, original_date, exception_type, new_start_time, new_end_time, reason) " +
            "VALUES(#{exceptionId}, #{ruleId}, #{originalDate}, #{exceptionType}, #{newStartTime}, #{newEndTime}, #{reason})")
    int insert(CourseException exception);
    
    @Select("SELECT * FROM course_exceptions WHERE exception_id = #{exceptionId}")
    CourseException selectById(String exceptionId);
    
    @Select("SELECT * FROM course_exceptions WHERE rule_id = #{ruleId} ORDER BY original_date")
    List<CourseException> selectByRuleId(String ruleId);
    
    @Select("SELECT * FROM course_exceptions WHERE rule_id = #{ruleId} AND original_date = #{originalDate}")
    CourseException selectByRuleIdAndDate(@Param("ruleId") String ruleId, @Param("originalDate") LocalDate originalDate);
    
    @Update("UPDATE course_exceptions SET exception_type = #{exceptionType}, new_start_time = #{newStartTime}, " +
            "new_end_time = #{newEndTime}, reason = #{reason} WHERE exception_id = #{exceptionId}")
    int updateById(CourseException exception);
    
    @Delete("DELETE FROM course_exceptions WHERE exception_id = #{exceptionId}")
    int deleteById(String exceptionId);
    
    @Delete("DELETE FROM course_exceptions WHERE rule_id = #{ruleId}")
    int deleteByRuleId(String ruleId);
}