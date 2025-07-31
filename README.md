# Sweet Moments Backend

<div align="center">

**情侣专属的生活协同与情感记录平台后端服务**

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-green)
![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue)
![License](https://img.shields.io/badge/License-MIT-yellow)

</div>

## 📖 项目简介

Sweet Moments 是一个专为情侣设计的生活协同与情感记录平台，旨在让工具更有温度，让日常更有爱。

**当前版本（V1.0）** 聚焦于 **课程日历管理** 功能，解决在线教师的排课混乱问题，为用户提供便捷的课程管理解决方案。

### ✨ 核心特性

- 🗓️ **智能课程管理** - 支持日/周/月视图的直观课程展示
- 🔄 **重复课程规则** - 灵活的重复模式设置（每日/每周/每月）
- 📝 **智能信息解析** - 批量导入杂乱课程信息，智能识别时间和学生
- 📊 **一键导出功能** - 支持Excel格式课表导出
- 🎨 **课程分类标签** - 阅读课/单词课/语法课/抗遗忘分类管理
- 🔔 **提醒通知** - 课前智能提醒功能
- 👥 **多用户权限** - 主要用户完整管理权限，伴侣查看权限

## 🎯 产品定位

| 项目要素 | 内容 |
|---------|-----|
| **项目名称** | 甜点时光（Sweet Moments） |
| **项目定位** | 情侣专属的生活协同与情感记录平台 |
| **当前阶段** | V1.0 - 课程日历管理 |
| **核心理念** | 让工具更有温度，让日常更有爱 |

## 🚀 技术栈

### 后端技术
- **框架**: Spring Boot 3.2.0
- **数据库**: MySQL 8.0+
- **ORM**: MyBatis 3.0.3
- **权限认证**: Sa-Token 1.37.0
- **缓存**: Redis (Spring Data Redis)
- **构建工具**: Maven
- **JDK版本**: Java 17

### 核心依赖
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>3.0.3</version>
    </dependency>
    <dependency>
        <groupId>cn.dev33</groupId>
        <artifactId>sa-token-spring-boot3-starter</artifactId>
        <version>1.37.0</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
</dependencies>
```

## 📁 项目结构

```
Sweet-Moments-backend/
├── src/main/java/com/springleaf/sweet/
│   ├── SweetMomentsApplication.java      # 启动类
│   ├── common/                           # 公共组件
│   │   ├── GlobalExceptionHandler.java  # 全局异常处理
│   │   ├── Result.java                   # 统一响应结果
│   │   └── WebConfig.java                # Web配置
│   ├── config/                           # 配置类
│   │   ├── MyBatisConfig.java           # MyBatis配置
│   │   └── SaTokenConfig.java           # Sa-Token配置
│   ├── controller/                       # 控制层
│   │   ├── UserController.java          # 用户相关接口
│   │   ├── CourseController.java        # 课程管理接口
│   │   ├── CourseRepeatRuleController.java  # 重复规则接口
│   │   ├── CourseExceptionController.java   # 课程异常接口
│   │   └── UserSettingsController.java  # 用户设置接口
│   ├── service/                          # 服务层
│   ├── mapper/                           # 数据访问层
│   ├── model/                            # 数据模型
│   │   ├── entity/                      # 实体类
│   │   ├── dto/                         # 数据传输对象
│   │   └── vo/                          # 视图对象
│   ├── enums/                           # 枚举类
│   └── utils/                           # 工具类
└── src/main/resources/
    ├── application.yml                   # 主配置文件
    └── application-dev.yml               # 开发环境配置
```

## 🗄️ 数据库设计

### 核心表结构

#### 用户表 (users)
存储用户基本信息和设置
```sql
user_id (UUID) | email | password_hash | nickname | partner_id | status
```

#### 课程表 (courses)
存储课程的基本信息
```sql
course_id (UUID) | user_id | student_name | course_type | start_time | end_time | note | status
```

#### 重复课程规则表 (course_repeat_rules)
管理重复课程的规则配置
```sql
rule_id (UUID) | course_id | repeat_type | repeat_interval | end_date | weekdays
```

#### 课程异常表 (course_exceptions)
记录重复课程的取消或改期信息
```sql
exception_id (UUID) | rule_id | original_date | exception_type | new_start_time
```

#### 用户设置表 (user_settings)
存储用户个性化设置
```sql
setting_id (UUID) | user_id | notification_before_minutes | default_course_duration
```

### 枚举类型定义

- **课程类型**: `reading`(阅读课) | `vocabulary`(单词课) | `grammar`(语法课) | `review`(抗遗忘)
- **重复类型**: `daily`(每日) | `weekly`(每周) | `monthly`(每月)
- **异常类型**: `cancel`(取消) | `reschedule`(改期)
- **课程状态**: `1`(正常) | `2`(已取消) | `3`(已完成)

## 🛠️ 快速开始

### 环境要求
- JDK 17+
- Maven 3.6+
- MySQL 8.0+
- Redis 6.0+

### 安装步骤

1. **克隆项目**
   ```bash
   git clone https://github.com/your-username/Sweet-Moments-backend.git
   cd Sweet-Moments-backend
   ```

2. **配置数据库**
   ```bash
   # 创建数据库
   mysql -u root -p
   CREATE DATABASE sweet_moments CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```

3. **修改配置文件**
   ```yaml
   # application-dev.yml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/sweet_moments
       username: your_username
       password: your_password
     redis:
       host: localhost
       port: 6379
   ```

4. **启动项目**
   ```bash
   mvn spring-boot:run
   ```

5. **访问接口**
   - 接口地址: http://localhost:8080
   - 接口文档: http://localhost:8080/doc.html (待集成)

## 📚 API文档

### 用户管理
- `POST /api/users/register` - 用户注册
- `POST /api/users/login` - 用户登录
- `GET /api/users/profile` - 获取用户信息
- `PUT /api/users/profile` - 更新用户信息

### 课程管理
- `GET /api/courses` - 获取课程列表
- `POST /api/courses` - 创建课程
- `PUT /api/courses/{id}` - 更新课程
- `DELETE /api/courses/{id}` - 删除课程
- `POST /api/courses/batch-import` - 批量导入课程
- `GET /api/courses/export` - 导出课程表

### 重复规则管理
- `POST /api/course-repeat-rules` - 创建重复规则
- `PUT /api/course-repeat-rules/{id}` - 更新重复规则
- `DELETE /api/course-repeat-rules/{id}` - 删除重复规则

## 🎨 课程分类与颜色方案

| 课程类型 | 颜色代码 | 颜色名称 | 用途说明 |
|---------|---------|---------|---------|
| 阅读课 | `#3498db` | 宁静蓝 | 阅读理解类课程 |
| 单词课 | `#2ecc71` | 活力绿 | 词汇学习类课程 |
| 语法课 | `#e67e22` | 温暖橙 | 语法教学类课程 |
| 抗遗忘 | `#95a5a6` | 中性灰 | 复习巩固类课程 |

## 🔮 版本规划

### 当前版本 (V1.0)
- ✅ 课程日历管理
- ✅ 重复课程规则
- ✅ 智能信息解析
- ✅ 课表导出功能

### 未来版本规划
- **V1.1**: 课程反馈系统
- **V2.0**: 情侣互动小游戏合集
- **V3.0**: 恋爱时间轴（记录重要时刻）
- **V3.1**: 纪念日提醒与倒计时
- **V4.0**: 旅行计划协作看板

## 🤝 贡献指南

1. Fork 本仓库
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 打开 Pull Request

## 📄 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情

## 💝 致谢

> **让每一节课都成为甜蜜时光的见证**  
> *—— springleaf 个人开发*

---

<div align="center">

**Sweet Moments Backend** ❤️ 

*为爱而生，因爱而美*

</div>