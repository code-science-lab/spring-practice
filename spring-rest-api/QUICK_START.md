# Spring Rest API 快速启动指南

## 🚀 快速启动

### 1. 启动应用

```bash
cd spring-rest-api
mvn spring-boot:run
```

### 2. 验证启动

应用启动成功后，您将看到类似以下输出：

```
Started Application in X.XXX seconds (process running for X.XXX)
```

### 3. 访问 API 文档

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/api-docs

## 📋 可用的 API 端点

### 用户管理 API

- `GET /api/users` - 获取所有用户
- `GET /api/users/{id}` - 根据 ID 获取用户
- `POST /api/users` - 创建新用户
- `PUT /api/users/{id}` - 更新用户
- `DELETE /api/users/{id}` - 删除用户

### 参数演示 API

- `GET /api/demo/path/{id}/category/{category}` - 路径变量演示
- `GET /api/demo/query` - 查询参数演示
- `POST /api/demo/body` - 请求体演示
- `GET /api/demo/headers` - 请求头演示
- `GET /api/demo/cookies` - Cookie 演示
- `GET /api/demo/attributes` - 请求属性演示
- `POST /api/demo/multipart` - 多部分请求演示
- `POST /api/demo/model` - 模型属性演示
- `GET /api/demo/request` - 原始请求对象演示
- `GET /api/demo/params-map` - 参数映射演示
- `POST /api/demo/mixed/{userId}` - 混合参数演示
- `POST /api/demo/set-cookie` - 设置 Cookie 演示

## 🧪 测试 API

### 方法 1：使用 Postman

1. 导入 `ParameterDemoController.postman_collection.json` 文件
2. 开始测试各种参数获取方式

### 方法 2：使用 curl 命令

```bash
# 测试路径变量
curl -X GET "http://localhost:8080/api/demo/path/123/category/electronics"

# 测试查询参数
curl -X GET "http://localhost:8080/api/demo/query?name=张三&age=25"

# 测试请求体
curl -X POST "http://localhost:8080/api/demo/body" \
  -H "Content-Type: application/json" \
  -d '{"name":"张三","email":"zhangsan@example.com"}'
```

### 方法 3：使用 Swagger UI

1. 访问 http://localhost:8080/swagger-ui.html
2. 找到"参数演示"部分
3. 点击"Try it out"进行交互式测试

## 🔧 常见问题解决

### 问题 1：端口 8080 被占用

```bash
# 修改端口
# 编辑 application.yml 文件，修改 server.port
server:
  port: 8081
```

### 问题 2：Java 版本问题

```bash
# 检查Java版本
java -version

# 确保使用Java 17+
export JAVA_HOME=/path/to/java17
```

### 问题 3：Maven 依赖问题

```bash
# 清理并重新下载依赖
mvn clean install -U
```

## 📚 学习资源

- [参数演示说明文档](PARAMETER_DEMO_README.md)
- [curl 命令集合](curl-commands.txt)
- [Postman 集合文件](ParameterDemoController.postman_collection.json)

## 🎯 下一步

1. 熟悉各种参数获取方式
2. 尝试修改和扩展 API
3. 学习 Spring Boot 最佳实践
4. 探索更多 Spring 框架功能
