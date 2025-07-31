# Sweet Moments 项目启动指南

## 项目概述
Sweet Moments（甜点时光）是一个情侣专属的生活协同与情感记录平台，当前版本专注于课程日历管理功能。

## 技术栈
- **前端**: UniApp + Vue3 + JavaScript
- **后端**: Spring Boot 3.2.0 + MyBatis + MySQL + Redis + Sa-Token
- **数据库**: MySQL 8.0
- **缓存**: Redis

## 环境要求
- JDK 17+
- MySQL 8.0+
- Redis 5.0+
- Node.js 16+

## 启动步骤

### 1. 后端启动

#### 1.1 数据库准备
```bash
# 启动MySQL服务
# Windows: 通过服务管理器启动MySQL
# macOS: brew services start mysql
# Linux: sudo systemctl start mysql

# 创建数据库并初始化数据
mysql -u root -p < sql/init.sql
```

#### 1.2 Redis准备
```bash
# 启动Redis服务
# Windows: redis-server.exe
# macOS: brew services start redis
# Linux: sudo systemctl start redis
```

#### 1.3 配置文件检查
确认 `src/main/resources/application-dev.yml` 中的配置：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/sweet-moments?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimeZone=UTC
    username: root
    password: 1234  # 修改为你的MySQL密码
  redis:
    host: localhost
    port: 6379
```

#### 1.4 启动后端服务
```bash
# 在后端项目根目录
cd D:\javatools\IDEAtool\javaproject\Sweet-Moments-backend
mvn clean compile
mvn spring-boot:run
```

后端服务启动在: http://localhost:9081

### 2. 前端启动

#### 2.1 安装依赖
```bash
# 在前端项目根目录
cd D:\WebFrontEnd\vue3\Sweet-Moments
npm install
```

#### 2.2 启动开发服务器
```bash
npm run dev
```

## 项目结构

### 后端结构
```
Sweet-Moments-backend/
├── src/main/java/com/springleaf/sweet/
│   ├── controller/          # 控制器层
│   ├── service/            # 业务逻辑层
│   ├── mapper/             # 数据访问层
│   ├── model/              # 数据模型
│   │   ├── entity/         # 实体类
│   │   ├── dto/            # 数据传输对象
│   │   └── vo/             # 视图对象
│   ├── common/             # 公共类
│   ├── config/             # 配置类
│   ├── enums/              # 枚举类
│   └── utils/              # 工具类
├── src/main/resources/
│   ├── application.yml     # 主配置文件
│   └── application-dev.yml # 开发环境配置
├── sql/                    # 数据库脚本
└── API_TEST.md            # API测试文档
```

### 前端结构
```
Sweet-Moments/
├── src/
│   ├── api/                # API接口封装
│   ├── components/         # 组件
│   ├── pages/              # 页面
│   ├── types/              # 类型定义
│   └── utils/              # 工具函数
├── uni.scss               # 全局样式
└── pages.json             # 页面配置
```

## 核心功能

### 用户功能
- ✅ 用户注册/登录
- ✅ 用户信息管理
- ✅ 伴侣账号绑定
- ✅ 个性化设置

### 课程管理功能
- ✅ 课程创建/编辑/删除
- ✅ 课程列表查询
- ✅ 时间范围查询
- ✅ 课程类型筛选
- ✅ 课程状态管理（正常/取消/完成）

### 课程类型
- **阅读课** (reading) - 蓝色标识
- **单词课** (vocabulary) - 绿色标识  
- **语法课** (grammar) - 橙色标识
- **抗遗忘** (review) - 灰色标识

## API文档
详见 `API_TEST.md` 文件，包含：
- 完整的API接口列表
- 请求/响应示例
- 测试用户账号
- 前端集成示例

## 测试账号
| 邮箱 | 密码 | 角色 |
|------|------|------|
| teacher@sweetmoments.com | 123456 | 老师 |
| student@sweetmoments.com | 123456 | 学生 |

## 开发注意事项

### 认证机制
- 使用Sa-Token进行权限认证
- Token有效期30天
- 前端需要在请求头中携带token: `Authorization: Bearer {token}`

### 跨域配置
后端已配置CORS，支持以下前端地址：
- http://localhost:5173
- http://localhost:3000
- http://127.0.0.1:5173
- http://127.0.0.1:3000

### 数据库设计
- 使用UUID作为主键
- 支持软删除（状态字段）
- 完整的索引优化
- 外键约束保证数据完整性

## 常见问题

### 1. 数据库连接失败
检查MySQL服务是否启动，用户名密码是否正确

### 2. Redis连接失败
检查Redis服务是否启动，端口是否正确

### 3. 跨域问题
检查前端请求地址是否在CORS配置中

### 4. Token失效
重新登录获取新的token

## 下一步开发计划
- 重复课程规则系统
- 课程提醒功能
- 数据导入导出
- 情侣互动功能