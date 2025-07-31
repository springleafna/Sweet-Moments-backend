# Sweet Moments API 测试文档

## 启动步骤

### 1. 数据库准备
```bash
# 确保MySQL服务已启动
# 执行初始化脚本
mysql -u root -p < sql/init.sql
```

### 2. Redis准备
```bash
# 确保Redis服务已启动
redis-server
```

### 3. 启动后端服务
```bash
# 在后端项目根目录执行
mvn spring-boot:run
```
后端服务将启动在 http://localhost:9081

### 4. 前端开发
前端API已配置为连接 http://localhost:9081

## API 接口测试

### 用户相关接口

#### 1. 用户注册
```http
POST http://localhost:9081/user/register
Content-Type: application/json

{
  "email": "test@example.com",
  "password": "123456",
  "nickname": "测试用户"
}
```

#### 2. 用户登录
```http
POST http://localhost:9081/user/login
Content-Type: application/json

{
  "email": "teacher@sweetmoments.com",
  "password": "123456"
}
```

#### 3. 获取用户信息
```http
GET http://localhost:9081/user/info
Authorization: Bearer {token}
```

#### 4. 更新用户信息
```http
PUT http://localhost:9081/user/info
Authorization: Bearer {token}
Content-Type: application/json

{
  "nickname": "新昵称",
  "theme": "dark"
}
```

#### 5. 绑定伴侣
```http
POST http://localhost:9081/user/bind-partner?partnerEmail=student@sweetmoments.com
Authorization: Bearer {token}
```

#### 6. 用户登出
```http
POST http://localhost:9081/user/logout
Authorization: Bearer {token}
```

### 课程相关接口

#### 1. 创建课程
```http
POST http://localhost:9081/course
Authorization: Bearer {token}
Content-Type: application/json

{
  "studentName": "张三",
  "courseType": "reading",
  "startTime": "2024-08-01T09:00:00",
  "endTime": "2024-08-01T10:00:00",
  "note": "阅读理解专项训练"
}
```

#### 2. 获取课程列表
```http
GET http://localhost:9081/course/list
Authorization: Bearer {token}
```

#### 3. 根据时间范围获取课程
```http
GET http://localhost:9081/course/list/time-range?startTime=2024-08-01 00:00:00&endTime=2024-08-01 23:59:59
Authorization: Bearer {token}
```

#### 4. 根据课程类型获取课程
```http
GET http://localhost:9081/course/list/type/reading
Authorization: Bearer {token}
```

#### 5. 获取单个课程详情
```http
GET http://localhost:9081/course/{courseId}
Authorization: Bearer {token}
```

#### 6. 更新课程
```http
PUT http://localhost:9081/course/{courseId}
Authorization: Bearer {token}
Content-Type: application/json

{
  "studentName": "张三",
  "courseType": "vocabulary",
  "startTime": "2024-08-01T10:00:00",
  "endTime": "2024-08-01T11:00:00",
  "note": "单词记忆训练"
}
```

#### 7. 取消课程
```http
POST http://localhost:9081/course/{courseId}/cancel
Authorization: Bearer {token}
```

#### 8. 完成课程
```http
POST http://localhost:9081/course/{courseId}/complete
Authorization: Bearer {token}
```

#### 9. 删除课程
```http
DELETE http://localhost:9081/course/{courseId}
Authorization: Bearer {token}
```

## 测试用户账号

| 邮箱 | 密码 | 角色 | 用户ID |
|------|------|------|--------|
| teacher@sweetmoments.com | 123456 | 老师 | test-user-001 |
| student@sweetmoments.com | 123456 | 学生 | test-user-002 |

## 课程类型枚举

| 类型 | 说明 | 颜色标识 |
|------|------|----------|
| reading | 阅读课 | 蓝色 |  
| vocabulary | 单词课 | 绿色 |
| grammar | 语法课 | 橙色 |
| review | 抗遗忘 | 灰色 |

## 课程状态枚举

| 状态码 | 说明 |
|--------|------|
| 1 | 正常 |
| 2 | 已取消 |
| 3 | 已完成 |

## 响应格式

### 成功响应
```json
{
  "code": 200,
  "message": "success",
  "data": {
    // 具体数据
  }
}
```

### 错误响应
```json
{
  "code": 400,
  "message": "错误信息",
  "data": null
}
```

## 常见错误码

| 错误码 | 说明 |
|--------|------|
| 401 | 未授权/token无效 |
| 403 | 禁止访问 |
| 404 | 资源不存在 |
| 500 | 服务器内部错误 |

## 前端集成示例

```javascript
// 登录示例
import { authApi } from '@/api/course.js'

async function login() {
  try {
    const result = await authApi.login({
      email: 'teacher@sweetmoments.com',
      password: '123456'
    })
    console.log('登录成功:', result)
  } catch (error) {
    console.error('登录失败:', error)
  }
}

// 获取课程列表示例
import { courseApi } from '@/api/course.js'

async function getCourses() {
  try {
    const courses = await courseApi.getCourses()
    console.log('课程列表:', courses)
  } catch (error) {
    console.error('获取课程失败:', error)
  }
}
```