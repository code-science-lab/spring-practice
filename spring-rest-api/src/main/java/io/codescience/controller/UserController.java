package io.codescience.controller;

import io.codescience.service.UserService;
import io.codescience.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "用户管理", description = "用户相关的所有操作，包括创建、查询、更新和删除用户")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "获取所有用户", description = "返回系统中所有用户的列表")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取用户列表", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class), examples = @ExampleObject(value = "[{\"id\":\"1\",\"name\":\"张三\",\"email\":\"zhangsan@example.com\"},{\"id\":\"2\",\"name\":\"李四\",\"email\":\"lisi@example.com\"}]"))),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据ID获取用户", description = "根据用户ID获取特定用户的详细信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取用户信息", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class), examples = @ExampleObject(value = "{\"id\":\"1\",\"name\":\"张三\",\"email\":\"zhangsan@example.com\"}"))),
            @ApiResponse(responseCode = "404", description = "用户不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public ResponseEntity<User> getUserById(
            @Parameter(description = "用户ID", required = true, example = "1") @PathVariable String id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    @Operation(summary = "创建新用户", description = "创建一个新的用户")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "用户创建成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class), examples = @ExampleObject(value = "{\"id\":\"3\",\"name\":\"王五\",\"email\":\"wangwu@example.com\"}"))),
            @ApiResponse(responseCode = "400", description = "请求参数错误"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public ResponseEntity<User> createUser(
            @Parameter(description = "用户信息", required = true) @RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新用户信息", description = "根据用户ID更新用户信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "用户信息更新成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class), examples = @ExampleObject(value = "{\"id\":\"1\",\"name\":\"张三（已更新）\",\"email\":\"zhangsan.updated@example.com\"}"))),
            @ApiResponse(responseCode = "404", description = "用户不存在"),
            @ApiResponse(responseCode = "400", description = "请求参数错误"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public ResponseEntity<User> updateUser(
            @Parameter(description = "用户ID", required = true, example = "1") @PathVariable String id,
            @Parameter(description = "更新的用户信息", required = true) @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除用户", description = "根据用户ID删除用户")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "用户删除成功"),
            @ApiResponse(responseCode = "404", description = "用户不存在"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "用户ID", required = true, example = "1") @PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}