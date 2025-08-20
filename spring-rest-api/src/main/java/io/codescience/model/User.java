package io.codescience.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "用户信息模型")
public class User {
    @Schema(description = "用户唯一标识符", example = "1")
    private String id;

    @Schema(description = "用户姓名", example = "张三", minLength = 1, maxLength = 100)
    private String name;

    @Schema(description = "用户邮箱地址", example = "zhangsan@example.com", format = "email")
    private String email;

    public User() {
    }

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}