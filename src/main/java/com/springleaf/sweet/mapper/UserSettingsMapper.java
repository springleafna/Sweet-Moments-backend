package com.springleaf.sweet.mapper;

import com.springleaf.sweet.model.entity.UserSettings;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserSettingsMapper {
    
    @Insert("INSERT INTO user_settings(setting_id, user_id, notification_before_minutes, quiet_start_time, quiet_end_time, " +
            "default_course_duration, week_start_day, timezone) " +
            "VALUES(#{settingId}, #{userId}, #{notificationBeforeMinutes}, #{quietStartTime}, #{quietEndTime}, " +
            "#{defaultCourseDuration}, #{weekStartDay}, #{timezone})")
    int insert(UserSettings settings);
    
    @Select("SELECT * FROM user_settings WHERE setting_id = #{settingId}")
    UserSettings selectById(String settingId);
    
    @Select("SELECT * FROM user_settings WHERE user_id = #{userId}")
    UserSettings selectByUserId(String userId);
    
    @Update("UPDATE user_settings SET notification_before_minutes = #{notificationBeforeMinutes}, " +
            "quiet_start_time = #{quietStartTime}, quiet_end_time = #{quietEndTime}, " +
            "default_course_duration = #{defaultCourseDuration}, week_start_day = #{weekStartDay}, " +
            "timezone = #{timezone} WHERE user_id = #{userId}")
    int updateByUserId(UserSettings settings);
    
    @Delete("DELETE FROM user_settings WHERE user_id = #{userId}")
    int deleteByUserId(String userId);
}