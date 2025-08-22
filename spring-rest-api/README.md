# Spring REST API 模块

这是一个基于 Spring Boot 的 REST API 服务，提供用户管理功能。

## 功能特性

- 用户 CRUD 操作（创建、读取、更新、删除）
- **参数获取方式演示**（12 种不同的参数绑定方式）
- OpenAPI 3.0 规范文档
- Swagger UI 界面
- RESTful API 设计

## API 端点

### 用户管理

| 方法   | 端点              | 描述             |
| ------ | ----------------- | ---------------- |
| GET    | `/api/users`      | 获取所有用户     |
| GET    | `/api/users/{id}` | 根据 ID 获取用户 |
| POST   | `/api/users`      | 创建新用户       |
| PUT    | `/api/users/{id}` | 更新用户信息     |
| DELETE | `/api/users/{id}` | 删除用户         |

### 参数演示

| 方法 | 端点                                      | 描述             |
| ---- | ----------------------------------------- | ---------------- |
| GET  | `/api/demo/path/{id}/category/{category}` | 路径变量演示     |
| GET  | `/api/demo/query`                         | 查询参数演示     |
| POST | `/api/demo/body`                          | 请求体演示       |
| GET  | `/api/demo/headers`                       | 请求头演示       |
| GET  | `/api/demo/cookies`                       | Cookie 演示      |
| GET  | `/api/demo/attributes`                    | 请求属性演示     |
| POST | `/api/demo/multipart`                     | 多部分请求演示   |
| POST | `/api/demo/model`                         | 模型属性演示     |
| GET  | `/api/demo/request`                       | 原始请求对象演示 |
| GET  | `/api/demo/params-map`                    | 参数映射演示     |
| POST | `/api/demo/mixed/{userId}`                | 混合参数演示     |
| POST | `/api/demo/set-cookie`                    | 设置 Cookie 演示 |

**详细说明请查看：** [参数演示说明文档](PARAMETER_DEMO_README.md)

**API 测试文件：**

- [Postman 集合文件](ParameterDemoController.postman_collection.json) - 可直接导入 Postman
- [curl 命令文件](curl-commands.txt) - 包含所有 API 调用的 curl 命令

## 用户模型

```json
{
  "id": "1",
  "name": "张三",
  "email": "zhangsan@example.com"
}
```

## 运行项目

### 前提条件

- Java 17+
- Maven 3.6+

### 启动应用

```bash
# 进入项目目录
cd spring-rest-api

# 编译项目
mvn clean compile

# 运行应用
mvn spring-boot:run
```

应用启动后，将在 `http://localhost:8080` 运行。

## API 文档

### Swagger UI

访问 `http://localhost:8080/swagger-ui.html` 查看交互式 API 文档。

### OpenAPI 规范

访问 `http://localhost:8080/api-docs` 获取 OpenAPI 3.0 规范的 JSON 格式文档。

### OpenAPI YAML 文件

项目根目录下的 `openapi.yaml` 文件包含了完整的 OpenAPI 3.0 规范文档。

## 示例请求

### 获取所有用户

```bash
curl -X GET "http://localhost:8080/api/users" \
  -H "Content-Type: application/json"
```

### 创建用户

```bash
curl -X POST "http://localhost:8080/api/users" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "王五",
    "email": "wangwu@example.com"
  }'
```

### 获取特定用户

```bash
curl -X GET "http://localhost:8080/api/users/1" \
  -H "Content-Type: application/json"
```

### 更新用户

```bash
curl -X PUT "http://localhost:8080/api/users/1" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "张三（已更新）",
    "email": "zhangsan.updated@example.com"
  }'
```

### 删除用户

```bash
curl -X DELETE "http://localhost:8080/api/users/1"
```

## 项目结构

```
spring-rest-api/
├── src/
│   ├── main/
│   │   ├── java/io/codescience/
│   │   │   ├── Application.java          # 主应用类
│   │   │   ├── config/
│   │   │   │   └── OpenApiConfig.java    # OpenAPI配置
│   │   │   ├── controller/
│   │   │   │   └── UserController.java   # 用户控制器
│   │   │   ├── model/
│   │   │   │   └── User.java             # 用户模型
│   │   │   ├── repository/               # 数据访问层
│   │   │   └── service/                  # 业务逻辑层
│   │   └── resources/
│   │       └── application.yml           # 应用配置
│   └── test/                             # 测试代码
├── pom.xml                               # Maven配置
├── openapi.yaml                          # OpenAPI规范文档
└── README.md                             # 项目说明
```

## 技术栈

- Spring Boot 3.x
- Spring Web MVC
- SpringDoc OpenAPI
- Maven
- Java 17

## 开发说明

### 添加新的 API 端点

1. 在相应的 Controller 类中添加新的方法
2. 使用 OpenAPI 注解（如`@Operation`、`@ApiResponse`等）添加文档
3. 更新 OpenAPI 配置（如需要）

### 修改 API 文档

- 修改 Controller 中的 OpenAPI 注解
- 更新`OpenApiConfig.java`中的配置
- 重新生成`openapi.yaml`文件（如需要）

## 故障排除

### 常见问题

1. **端口被占用**

   - 修改`application.yml`中的`server.port`配置

2. **API 文档无法访问**

   - 确保 SpringDoc 依赖已正确添加
   - 检查`OpenApiConfig.java`配置

3. **编译错误**

   - 确保 Java 版本为 17+
   - 运行`mvn clean compile`重新编译

4. **路径映射冲突**
   - 已解决：删除了重复的`UserDemoController`
   - 确保每个 API 端点路径唯一

## 许可证

MIT License
