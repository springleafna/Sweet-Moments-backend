package com.springleaf.sweet.mapper;

import com.springleaf.sweet.model.entity.CourseRepeatRule;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseRepeatRuleMapper {
    
    @Insert("INSERT INTO course_repeat_rules(rule_id, course_id, repeat_type, repeat_interval, end_date, weekdays, monthly_type, status) " +
            "VALUES(#{ruleId}, #{courseId}, #{repeatType}, #{repeatInterval}, #{endDate}, #{weekdays}, #{monthlyType}, #{status})")
    int insert(CourseRepeatRule rule);
    
    @Select("SELECT * FROM course_repeat_rules WHERE rule_id = #{ruleId}")
    CourseRepeatRule selectById(String ruleId);
    
    @Select("SELECT * FROM course_repeat_rules WHERE course_id = #{courseId} AND status = 1")
    List<CourseRepeatRule> selectByCourseId(String courseId);
    
    @Select("SELECT * FROM course_repeat_rules WHERE status = 1 ORDER BY created_at DESC")
    List<CourseRepeatRule> selectAll();
    
    @Update("UPDATE course_repeat_rules SET repeat_type = #{repeatType}, repeat_interval = #{repeatInterval}, " +
            "end_date = #{endDate}, weekdays = #{weekdays}, monthly_type = #{monthlyType} WHERE rule_id = #{ruleId}")
    int updateById(CourseRepeatRule rule);
    
    @Update("UPDATE course_repeat_rules SET status = #{status} WHERE rule_id = #{ruleId}")
    int updateStatus(@Param("ruleId") String ruleId, @Param("status") Integer status);
    
    @Delete("DELETE FROM course_repeat_rules WHERE rule_id = #{ruleId}")
    int deleteById(String ruleId);
}