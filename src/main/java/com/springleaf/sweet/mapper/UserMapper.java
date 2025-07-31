package com.springleaf.sweet.mapper;

import com.springleaf.sweet.model.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    
    @Insert("INSERT INTO users(user_id, email, password_hash, nickname, avatar_url, partner_id, status, notification_enabled, theme, language) " +
            "VALUES(#{userId}, #{email}, #{passwordHash}, #{nickname}, #{avatarUrl}, #{partnerId}, #{status}, #{notificationEnabled}, #{theme}, #{language})")
    int insert(User user);
    
    @Select("SELECT * FROM users WHERE user_id = #{userId}")
    User selectById(String userId);
    
    @Select("SELECT * FROM users WHERE email = #{email}")
    User selectByEmail(String email);
    
    @Update("UPDATE users SET nickname = #{nickname}, avatar_url = #{avatarUrl}, theme = #{theme}, language = #{language}, " +
            "notification_enabled = #{notificationEnabled} WHERE user_id = #{userId}")
    int updateById(User user);
    
    @Update("UPDATE users SET partner_id = #{partnerId} WHERE user_id = #{userId}")
    int updatePartnerId(@Param("userId") String userId, @Param("partnerId") String partnerId);
    
    @Update("UPDATE users SET status = #{status} WHERE user_id = #{userId}")
    int updateStatus(@Param("userId") String userId, @Param("status") Integer status);
    
    @Delete("DELETE FROM users WHERE user_id = #{userId}")
    int deleteById(String userId);
    
    @Select("SELECT * FROM users WHERE status = 1 ORDER BY created_at DESC")
    List<User> selectAll();
}