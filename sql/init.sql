-- 甜点时光数据库初始化脚本
-- 创建数据库
CREATE DATABASE IF NOT EXISTS `sweet-moments` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `sweet-moments`;

-- 创建用户表
CREATE TABLE users (
    user_id VARCHAR(36) PRIMARY KEY COMMENT '用户唯一标识（UUID）',
    email VARCHAR(255) NOT NULL UNIQUE COMMENT '用户邮箱',
    password_hash VARCHAR(255) NOT NULL COMMENT '密码哈希值',
    nickname VARCHAR(50) COMMENT '用户昵称',
    avatar_url VARCHAR(500) COMMENT '头像URL',
    partner_id VARCHAR(36) COMMENT '伴侣用户ID',
    status TINYINT DEFAULT 1 COMMENT '用户状态：1-正常，0-禁用',
    notification_enabled BOOLEAN DEFAULT TRUE COMMENT '是否开启通知',
    theme VARCHAR(10) DEFAULT 'light' COMMENT '主题设置：light-浅色，dark-深色',
    language VARCHAR(10) DEFAULT 'zh-CN' COMMENT '语言设置',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    
    INDEX idx_email (email),
    INDEX idx_partner_id (partner_id),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- 创建课程表
CREATE TABLE courses (
    course_id VARCHAR(36) PRIMARY KEY COMMENT '课程唯一标识（UUID）',
    user_id VARCHAR(36) NOT NULL COMMENT '创建用户ID',
    student_name VARCHAR(50) NOT NULL COMMENT '学生姓名',
    course_type ENUM('reading', 'vocabulary', 'grammar', 'review') NOT NULL COMMENT '课程类型：阅读课、单词课、语法课、抗遗忘',
    start_time DATETIME NOT NULL COMMENT '开始时间',
    end_time DATETIME NOT NULL COMMENT '结束时间',
    note TEXT COMMENT '备注信息',
    status TINYINT DEFAULT 1 COMMENT '课程状态：1-正常，2-已取消，3-已完成',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    
    INDEX idx_user_id (user_id),
    INDEX idx_start_time (start_time),
    INDEX idx_course_type (course_type),
    INDEX idx_student_name (student_name),
    INDEX idx_status (status),
    INDEX idx_time_range (start_time, end_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='课程表';

-- 创建重复课程规则表
CREATE TABLE course_repeat_rules (
    rule_id VARCHAR(36) PRIMARY KEY COMMENT '规则唯一标识（UUID）',
    course_id VARCHAR(36) NOT NULL COMMENT '关联课程ID',
    repeat_type ENUM('daily', 'weekly', 'monthly') NOT NULL COMMENT '重复类型：每日、每周、每月',
    repeat_interval INT DEFAULT 1 COMMENT '重复间隔（如每2周）',
    end_date DATE COMMENT '重复结束日期，NULL表示无限重复',
    weekdays VARCHAR(20) COMMENT '重复星期（如1,3,5表示周一三五）',
    monthly_type ENUM('date', 'weekday') COMMENT '月重复类型：按日期或按星期',
    status TINYINT DEFAULT 1 COMMENT '规则状态：1-生效，0-停用',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    
    INDEX idx_course_id (course_id),
    INDEX idx_repeat_type (repeat_type),
    INDEX idx_end_date (end_date),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='重复课程规则表';

-- 创建课程异常表
CREATE TABLE course_exceptions (
    exception_id VARCHAR(36) PRIMARY KEY COMMENT '异常唯一标识（UUID）',
    rule_id VARCHAR(36) NOT NULL COMMENT '关联重复规则ID',
    original_date DATE NOT NULL COMMENT '原始课程日期',
    exception_type ENUM('cancel', 'reschedule') NOT NULL COMMENT '异常类型：取消、改期',
    new_start_time DATETIME COMMENT '新开始时间（改期时使用）',
    new_end_time DATETIME COMMENT '新结束时间（改期时使用）',
    reason VARCHAR(255) COMMENT '异常原因',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    
    INDEX idx_rule_id (rule_id),
    INDEX idx_original_date (original_date),
    INDEX idx_exception_type (exception_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='课程异常表（重复课程的取消或改期记录）';

-- 创建用户设置表
CREATE TABLE user_settings (
    setting_id VARCHAR(36) PRIMARY KEY COMMENT '设置唯一标识（UUID）',
    user_id VARCHAR(36) NOT NULL UNIQUE COMMENT '用户ID',
    notification_before_minutes INT DEFAULT 15 COMMENT '课前提醒分钟数',
    quiet_start_time TIME COMMENT '免打扰开始时间',
    quiet_end_time TIME COMMENT '免打扰结束时间',
    default_course_duration INT DEFAULT 60 COMMENT '默认课程时长（分钟）',
    week_start_day TINYINT DEFAULT 1 COMMENT '一周开始日：1-周一，0-周日',
    timezone VARCHAR(50) DEFAULT 'Asia/Shanghai' COMMENT '时区设置',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    
    INDEX idx_user_id (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户个性化设置表';

-- 插入测试数据
-- 插入测试用户
-- 密码123456
INSERT INTO users (user_id, email, password_hash, nickname, status, notification_enabled, theme, language, created_at, updated_at) VALUES
('test-user-001', 'teacher@sweetmoments.com', '$2a$10$N.zmdr9k7uOCQb376NoUnOmvWUor5EtJOH7Vr1jGAMXGW7H0fPZXK', '英语老师小雨', 1, TRUE, 'light', 'zh-CN', NOW(), NOW()),
('test-user-002', 'student@sweetmoments.com', '$2a$10$N.zmdr9k7uOCQb376NoUnOmvWUor5EtJOH7Vr1jGAMXGW7H0fPZXK', '学习伙伴小明', 1, TRUE, 'light', 'zh-CN', NOW(), NOW());

-- 设置伴侣关系
UPDATE users SET partner_id = 'test-user-002' WHERE user_id = 'test-user-001';
UPDATE users SET partner_id = 'test-user-001' WHERE user_id = 'test-user-002';

-- 插入测试课程数据
INSERT INTO courses (course_id, user_id, student_name, course_type, start_time, end_time, note, status, created_at, updated_at) VALUES
('course-001', 'test-user-001', '张小明', 'reading', '2024-08-01 09:00:00', '2024-08-01 10:00:00', '阅读理解专项训练', 1, NOW(), NOW()),
('course-002', 'test-user-001', '李小红', 'vocabulary', '2024-08-01 10:30:00', '2024-08-01 11:30:00', '单词记忆法练习', 1, NOW(), NOW()),
('course-003', 'test-user-001', '王小华', 'grammar', '2024-08-01 14:00:00', '2024-08-01 15:00:00', '语法结构分析', 1, NOW(), NOW()),
('course-004', 'test-user-001', '赵小强', 'review', '2024-08-01 15:30:00', '2024-08-01 16:30:00', '综合复习课程', 1, NOW(), NOW());

-- 插入用户设置
INSERT INTO user_settings (setting_id, user_id, notification_before_minutes, default_course_duration, week_start_day, timezone, created_at, updated_at) VALUES
('setting-001', 'test-user-001', 15, 60, 1, 'Asia/Shanghai', NOW(), NOW()),
('setting-002', 'test-user-002', 10, 45, 1, 'Asia/Shanghai', NOW(), NOW());

-- 显示初始化完成信息
SELECT '数据库初始化完成！' AS message;
SELECT COUNT(*) AS user_count FROM users;
SELECT COUNT(*) AS course_count FROM courses;
SELECT COUNT(*) AS settings_count FROM user_settings;