# Spring Data JPA 示例

本项目演示了 Spring Boot 中 `Spring Data JPA` 的使用，包括基本的 CRUD 操作、批量插入、模糊查询和聚合统计等功能。与 JdbcTemplate 版本相比，JPA 版本提供了更简洁的代码和更强大的查询能力。

## 技术栈

- Spring Boot 3.1.0
- Spring Data JPA
- Hibernate
- MySQL 8.0
- Java 17

## 快速开始

### 1. 数据库配置

确保 MySQL 8.0 已启动，并创建数据库：

```sql
CREATE DATABASE testdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 2. 修改数据库连接配置

编辑 `src/main/resources/application.properties`，修改数据库连接信息：

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/testdb?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=你的用户名
spring.datasource.password=你的密码
```

### 3. 启动应用

应用将在 `http://localhost:8083` 启动。

## API 接口文档

### 1. 创建单个产品

**POST** `/api/products`

```bash
curl -X POST http://localhost:8083/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "iPhone 15",
    "price": 5999.00
  }'
```

**响应**: 201 Created

### 2. 批量创建产品

**POST** `/api/products/batch`

```bash
curl -X POST http://localhost:8083/api/products/batch \
  -H "Content-Type: application/json" \
  -d '[
    {
      "name": "MacBook Pro",
      "price": 12999.00
    },
    {
      "name": "iPad Air",
      "price": 4399.00
    },
    {
      "name": "AirPods Pro",
      "price": 1899.00
    }
  ]'
```

**响应**: 返回保存的产品列表

### 3. 查询所有产品

**GET** `/api/products`

```bash
curl http://localhost:8083/api/products
```

**响应示例**:

```json
[
  {
    "id": 1,
    "name": "Apple",
    "price": 3.5
  },
  {
    "id": 2,
    "name": "Banana",
    "price": 2.0
  },
  {
    "id": 3,
    "name": "Coffee",
    "price": 25.0
  }
]
```

### 4. 根据名称模糊查询

**GET** `/api/products?name=关键词`

```bash
# 查询名称包含 "phone" 的产品
curl "http://localhost:8083/api/products?name=phone"

# 查询名称包含 "Mac" 的产品
curl "http://localhost:8083/api/products?name=Mac"
```

### 5. 根据 ID 查询单个产品

**GET** `/api/products/{id}`

```bash
curl http://localhost:8083/api/products/1
```

**响应示例**:

```json
{
  "id": 1,
  "name": "Apple",
  "price": 3.5
}
```

### 6. 更新产品价格

**PUT** `/api/products/{id}/price/{price}`

```bash
# 将 ID 为 1 的产品价格更新为 4.0
curl -X PUT http://localhost:8083/api/products/1/price/4.0
```

**响应**: "Product price updated successfully" 或 404 Not Found

### 7. 删除产品

**DELETE** `/api/products/{id}`

```bash
# 删除 ID 为 3 的产品
curl -X DELETE http://localhost:8083/api/products/3
```

**响应**: "Product deleted successfully" 或 404 Not Found

### 8. 获取产品统计信息

**GET** `/api/products/stats`

```bash
curl http://localhost:8083/api/products/stats
```

**响应示例**:

```json
{
  "cnt": 5,
  "avgPrice": 15.3
}
```

## 完整测试流程

以下是一个完整的测试流程，演示所有功能：

```bash
# 1. 查看初始数据
echo "=== 查看初始数据 ==="
curl http://localhost:8083/api/products

# 2. 创建单个产品
echo -e "\n=== 创建单个产品 ==="
curl -X POST http://localhost:8083/api/products \
  -H "Content-Type: application/json" \
  -d '{"name": "iPhone 15", "price": 5999.00}'

# 3. 批量创建产品
echo -e "\n=== 批量创建产品 ==="
curl -X POST http://localhost:8083/api/products/batch \
  -H "Content-Type: application/json" \
  -d '[
    {"name": "MacBook Pro", "price": 12999.00},
    {"name": "iPad Air", "price": 4399.00},
    {"name": "AirPods Pro", "price": 1899.00}
  ]'

# 4. 查看所有产品
echo -e "\n=== 查看所有产品 ==="
curl http://localhost:8083/api/products

# 5. 模糊查询
echo -e "\n=== 模糊查询包含 'Pro' 的产品 ==="
curl "http://localhost:8083/api/products?name=Pro"

# 6. 更新价格
echo -e "\n=== 更新 iPhone 15 的价格 ==="
curl -X PUT http://localhost:8083/api/products/4/price/5499.00

# 7. 查看统计信息
echo -e "\n=== 查看统计信息 ==="
curl http://localhost:8083/api/products/stats

# 8. 删除一个产品
echo -e "\n=== 删除 AirPods Pro ==="
curl -X DELETE http://localhost:8083/api/products/7

# 9. 最终查看所有产品
echo -e "\n=== 最终产品列表 ==="
curl http://localhost:8083/api/products
```

## 技术要点

### Spring Data JPA 使用示例

1. **实体类 (Entity)**

   - `@Entity`: 标记为 JPA 实体
   - `@Table`: 指定表名
   - `@Id`: 主键标识
   - `@GeneratedValue`: 主键生成策略
   - `@Column`: 列映射配置

2. **仓库接口 (Repository)**

   - 继承 `JpaRepository`: 获得基本的 CRUD 操作
   - 方法名查询: `findByNameContainingOrderById()`
   - `@Query`: 自定义 JPQL 查询
   - 批量操作: `saveAll()`

3. **服务层 (Service)**

   - `@Service`: 标记为服务类
   - `@Transactional`: 事务管理
   - 业务逻辑封装

4. **控制器 (Controller)**
   - `@RestController`: REST API 控制器
   - `@RequestMapping`: 请求映射
   - 统一的响应格式

### JPA 配置

- `spring.jpa.hibernate.ddl-auto=create-drop`: 每次启动重新创建表
- `spring.jpa.show-sql=true`: 显示 SQL 语句
- `spring.jpa.properties.hibernate.format_sql=true`: 格式化 SQL
- `spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect`: MySQL 8 方言

### 与 JdbcTemplate 的对比

| 特性     | JdbcTemplate | Spring Data JPA |
| -------- | ------------ | --------------- |
| 代码量   | 较多         | 较少            |
| 类型安全 | 否           | 是              |
| 查询方式 | SQL          | 方法名、JPQL    |
| 性能     | 高           | 中等            |
| 学习曲线 | 陡峭         | 平缓            |
| 维护性   | 中等         | 高              |

## 注意事项

1. 确保 MySQL 服务已启动
2. 修改数据库连接配置中的用户名和密码
3. 确保数据库 `testdb` 已创建
4. 应用端口为 8083，避免与其他服务冲突
5. JPA 会自动创建表结构，无需手动执行 schema.sql

## 故障排除

1. **连接失败**: 检查 MySQL 服务状态和连接配置
2. **表不存在**: JPA 会自动创建表，检查 `ddl-auto` 配置
3. **端口冲突**: 修改 `application.properties` 中的 `server.port`
4. **方言错误**: 确保使用正确的 Hibernate 方言
