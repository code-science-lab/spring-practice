# Spring Rest API 参数获取方式演示

这个控制器演示了 Spring Rest API 中所有的参数获取方式，每个端点都返回接收到的参数 JSON 内容。

## 启动应用

```bash
cd spring-rest-api
mvn spring-boot:run
```

应用启动后访问：http://localhost:8080/swagger-ui.html

## API 端点列表

### 1. 路径变量演示 - @PathVariable

**端点：** `GET /api/demo/path/{id}/category/{category}`

**示例：**

```bash
curl -X GET "http://localhost:8080/api/demo/path/123/category/electronics"
```

**响应：**

```json
{
  "id": "123",
  "category": "electronics",
  "message": "路径变量获取成功"
}
```

### 2. 查询参数演示 - @RequestParam

**端点：** `GET /api/demo/query`

**示例：**

```bash
curl -X GET "http://localhost:8080/api/demo/query?name=张三&age=25&hobbies=读书&hobbies=游泳&active=true"
```

**响应：**

```json
{
  "name": "张三",
  "age": 25,
  "hobbies": ["读书", "游泳"],
  "active": true,
  "message": "查询参数获取成功"
}
```

### 3. 请求体演示 - @RequestBody

**端点：** `POST /api/demo/body`

**示例：**

```bash
curl -X POST "http://localhost:8080/api/demo/body" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "张三",
    "email": "zhangsan@example.com",
    "age": 25
  }'
```

**响应：**

```json
{
  "receivedData": {
    "name": "张三",
    "email": "zhangsan@example.com",
    "age": 25
  },
  "message": "请求体获取成功"
}
```

### 4. 请求头演示 - @RequestHeader

**端点：** `GET /api/demo/headers`

**示例：**

```bash
curl -X GET "http://localhost:8080/api/demo/headers" \
  -H "User-Agent: MyApp/1.0" \
  -H "Accept-Language: zh-CN,zh;q=0.9" \
  -H "Authorization: Bearer token123"
```

**响应：**

```json
{
  "userAgent": "MyApp/1.0",
  "acceptLanguage": "zh-CN,zh;q=0.9",
  "authorization": "Bearer token123",
  "message": "请求头获取成功"
}
```

### 5. Cookie 演示 - @CookieValue

**端点：** `GET /api/demo/cookies`

**示例：**

```bash
curl -X GET "http://localhost:8080/api/demo/cookies" \
  -H "Cookie: sessionId=abc123; theme=dark; preferences=compact"
```

**响应：**

```json
{
  "sessionId": "abc123",
  "theme": "dark",
  "preferences": "compact",
  "message": "Cookie获取成功"
}
```

### 6. 请求属性演示 - @RequestAttribute

**端点：** `GET /api/demo/attributes`

**注意：** 这个端点需要拦截器或过滤器预先设置请求属性才能看到效果。

**响应：**

```json
{
  "userId": null,
  "userRole": null,
  "message": "请求属性获取成功"
}
```

### 7. 多部分请求演示 - @RequestPart

**端点：** `POST /api/demo/multipart`

**示例：**

```bash
curl -X POST "http://localhost:8080/api/demo/multipart" \
  -F "file=@test.txt" \
  -F 'metadata={"description":"测试文件","tags":["demo"]}'
```

**响应：**

```json
{
  "fileName": "test.txt",
  "fileSize": 1024,
  "contentType": "text/plain",
  "metadata": {
    "description": "测试文件",
    "tags": ["demo"]
  },
  "message": "多部分请求处理成功"
}
```

### 8. 模型属性演示 - @ModelAttribute

**端点：** `POST /api/demo/model`

**示例：**

```bash
curl -X POST "http://localhost:8080/api/demo/model" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "name=张三&age=25&email=zhangsan@example.com"
```

**响应：**

```json
{
  "receivedData": {
    "name": "张三",
    "age": "25",
    "email": "zhangsan@example.com"
  },
  "message": "模型属性获取成功"
}
```

### 9. 原始请求对象演示 - HttpServletRequest

**端点：** `GET /api/demo/request`

**示例：**

```bash
curl -X GET "http://localhost:8080/api/demo/request?param=value"
```

**响应：**

```json
{
  "method": "GET",
  "requestURI": "/api/demo/request",
  "queryString": "param=value",
  "remoteAddr": "127.0.0.1",
  "userAgent": "curl/7.68.0",
  "message": "原始请求对象获取成功"
}
```

### 10. 参数映射演示 - @RequestParam Map

**端点：** `GET /api/demo/params-map`

**示例：**

```bash
curl -X GET "http://localhost:8080/api/demo/params-map?name=张三&age=25&city=北京&hobby=读书"
```

**响应：**

```json
{
  "allParams": {
    "name": "张三",
    "age": "25",
    "city": "北京",
    "hobby": "读书"
  },
  "paramCount": 4,
  "message": "参数映射获取成功"
}
```

### 11. 混合参数演示

**端点：** `POST /api/demo/mixed/{userId}`

**示例：**

```bash
curl -X POST "http://localhost:8080/api/demo/mixed/123?action=update" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer token123" \
  -H "Cookie: sessionId=abc123" \
  -d '{
    "name": "张三",
    "email": "zhangsan@example.com"
  }'
```

**响应：**

```json
{
  "pathVariable": "123",
  "queryParam": "update",
  "requestBody": {
    "name": "张三",
    "email": "zhangsan@example.com"
  },
  "authHeader": "Bearer token123",
  "sessionId": "abc123",
  "message": "混合参数获取成功"
}
```

### 12. 设置 Cookie 演示

**端点：** `POST /api/demo/set-cookie`

**示例：**

```bash
curl -X POST "http://localhost:8080/api/demo/set-cookie?name=theme&value=dark" \
  -c cookies.txt
```

**响应：**

```json
{
  "cookieName": "theme",
  "cookieValue": "dark",
  "message": "Cookie设置成功"
}
```

## 参数获取方式总结

| 注解               | 用途       | 数据来源            | 适用场景           |
| ------------------ | ---------- | ------------------- | ------------------ |
| @PathVariable      | 路径变量   | URL 路径            | RESTful 资源标识   |
| @RequestParam      | 查询参数   | URL 查询字符串      | 过滤、分页、搜索   |
| @RequestBody       | 请求体     | HTTP 请求体         | 复杂数据提交       |
| @RequestHeader     | 请求头     | HTTP 请求头         | 认证、设备信息     |
| @CookieValue       | Cookie 值  | HTTP Cookie         | 会话管理、用户偏好 |
| @RequestAttribute  | 请求属性   | 请求作用域          | 拦截器传递数据     |
| @RequestPart       | 多部分数据 | multipart/form-data | 文件上传           |
| @ModelAttribute    | 模型属性   | 表单数据            | 传统表单提交       |
| HttpServletRequest | 原始请求   | 完整 HTTP 请求      | 需要完整请求信息   |
| @RequestParam Map  | 参数映射   | 所有查询参数        | 动态参数处理       |

## 测试建议

1. **使用 Swagger UI**：访问 http://localhost:8080/swagger-ui.html 进行交互式测试
2. **使用 curl 命令**：按照上述示例进行命令行测试
3. **使用 Postman**：导入 API 文档进行图形化测试
4. **组合测试**：尝试多种参数获取方式的组合使用

## 注意事项

1. **类型转换**：Spring 会自动进行基本类型转换
2. **默认值**：使用`defaultValue`属性设置默认值
3. **必填参数**：使用`required = true`设置必填参数
4. **集合参数**：支持 List、Set 等集合类型
5. **文件上传**：需要配置 multipart 支持
6. **编码问题**：确保正确处理中文等特殊字符
