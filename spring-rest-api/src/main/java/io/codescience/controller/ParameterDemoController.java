package io.codescience.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/demo")
@Tag(name = "参数演示", description = "演示Spring Rest API中所有的参数获取方式")
public class ParameterDemoController {

    public ParameterDemoController() {

    }
    /**
     * 1. @PathVariable - 路径变量
     */
    @GetMapping("/path/{id}/category/{category}")
    @Operation(summary = "路径变量演示", description = "演示@PathVariable的使用")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取路径变量", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"id\":\"123\",\"category\":\"electronics\"}")))
    })
    public ResponseEntity<Map<String, Object>> pathVariableDemo(
            @Parameter(description = "资源ID", example = "123") @PathVariable(value = "id") String userId,
            @Parameter(description = "资源分类", example = "electronics") @PathVariable String category) {

        Map<String, Object> result = new HashMap<>();
        result.put("id", userId);
        result.put("category", category);
        result.put("message", "路径变量获取成功");

        return ResponseEntity.ok(result);
    }

    /**
     * 2. @RequestParam - 查询参数
     */
    @GetMapping("/query")
    @Operation(summary = "查询参数演示", description = "演示@RequestParam的使用")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取查询参数", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"name\":\"张三\",\"age\":25,\"hobbies\":[\"读书\",\"游泳\"]}")))
    })
    public ResponseEntity<Map<String, Object>> requestParamDemo(
            @Parameter(description = "用户姓名") @RequestParam(value = "name", required = false) String name,
            @Parameter(description = "用户年龄") @RequestParam(value = "age", defaultValue = "18") Integer age,
            @Parameter(description = "用户爱好") @RequestParam(value = "hobbies", required = false) List<String> hobbies,
            @Parameter(description = "是否激活") @RequestParam(value = "active", defaultValue = "true") Boolean active) {

        Map<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("age", age);
        result.put("hobbies", hobbies);
        result.put("active", active);
        result.put("message", "查询参数获取成功");

        return ResponseEntity.ok(result);
    }

    /**
     * 3. @RequestBody - 请求体
     */
    @PostMapping("/body")
    @Operation(summary = "请求体演示", description = "演示@RequestBody的使用")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取请求体", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"receivedData\":{\"name\":\"张三\",\"email\":\"zhangsan@example.com\"}}")))
    })
    public ResponseEntity<Map<String, Object>> requestBodyDemo(
            @Parameter(description = "用户信息") @RequestBody Map<String, Object> userData) {

        Map<String, Object> result = new HashMap<>();
        result.put("receivedData", userData);
        result.put("message", "请求体获取成功");

        return ResponseEntity.ok(result);
    }

    /**
     * 4. @RequestHeader - 请求头
     */
    @GetMapping("/headers")
    @Operation(summary = "请求头演示", description = "演示@RequestHeader的使用")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取请求头", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"userAgent\":\"Mozilla/5.0...\",\"acceptLanguage\":\"zh-CN,zh;q=0.9\"}")))
    })
    public ResponseEntity<Map<String, Object>> requestHeaderDemo(
            @Parameter(description = "用户代理") @RequestHeader("User-Agent") String userAgent,
            @Parameter(description = "接受语言") @RequestHeader(value = "Accept-Language", defaultValue = "zh-CN") String acceptLanguage,
            @Parameter(description = "授权令牌") @RequestHeader(value = "Authorization", required = false) String authorization) {

        Map<String, Object> result = new HashMap<>();
        result.put("userAgent", userAgent);
        result.put("acceptLanguage", acceptLanguage);
        result.put("authorization", authorization);
        result.put("message", "请求头获取成功");

        return ResponseEntity.ok(result);
    }

    /**
     * 5. @CookieValue - Cookie值
     */
    @GetMapping("/cookies")
    @Operation(summary = "Cookie演示", description = "演示@CookieValue的使用")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取Cookie", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"sessionId\":\"abc123\",\"theme\":\"dark\"}")))
    })
    public ResponseEntity<Map<String, Object>> cookieValueDemo(
            @Parameter(description = "会话ID") @CookieValue(value = "sessionId", required = false) String sessionId,
            @Parameter(description = "主题设置") @CookieValue(value = "theme", defaultValue = "light") String theme,
            @Parameter(description = "用户偏好") @CookieValue(value = "preferences", required = false) String preferences) {

        Map<String, Object> result = new HashMap<>();
        result.put("sessionId", sessionId);
        result.put("theme", theme);
        result.put("preferences", preferences);
        result.put("message", "Cookie获取成功");

        return ResponseEntity.ok(result);
    }

    /**
     * 6. @RequestAttribute - 请求属性
     */
    @GetMapping("/attributes")
    @Operation(summary = "请求属性演示", description = "演示@RequestAttribute的使用")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取请求属性", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"userId\":\"123\",\"userRole\":\"admin\"}")))
    })
    public ResponseEntity<Map<String, Object>> requestAttributeDemo(
            @Parameter(description = "用户ID") @RequestAttribute(value = "userId", required = false) String userId,
            @Parameter(description = "用户角色") @RequestAttribute(value = "userRole", required = false) String userRole) {

        Map<String, Object> result = new HashMap<>();
        result.put("userId", userId);
        result.put("userRole", userRole);
        result.put("message", "请求属性获取成功");

        return ResponseEntity.ok(result);
    }

    /**
     * 7. @RequestPart - 多部分请求
     */
    @PostMapping("/multipart")
    @Operation(summary = "多部分请求演示", description = "演示@RequestPart的使用")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功处理多部分请求", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"fileName\":\"test.txt\",\"fileSize\":1024,\"metadata\":{\"description\":\"测试文件\"}}")))
    })
    public ResponseEntity<Map<String, Object>> requestPartDemo(
            @Parameter(description = "上传文件") @RequestPart("file") MultipartFile file,
            @Parameter(description = "文件元数据") @RequestPart("metadata") Map<String, Object> metadata) {

        Map<String, Object> result = new HashMap<>();
        result.put("fileName", file.getOriginalFilename());
        result.put("fileSize", file.getSize());
        result.put("contentType", file.getContentType());
        result.put("metadata", metadata);
        result.put("message", "多部分请求处理成功");

        return ResponseEntity.ok(result);
    }

    /**
     * 8. @ModelAttribute - 模型属性
     */
    @PostMapping("/model")
    @Operation(summary = "模型属性演示", description = "演示@ModelAttribute的使用")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取模型属性", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"name\":\"张三\",\"age\":25}")))
    })
    public ResponseEntity<Map<String, Object>> modelAttributeDemo(
            @Parameter(description = "用户信息") @ModelAttribute Map<String, Object> userInfo) {

        Map<String, Object> result = new HashMap<>();
        result.put("receivedData", userInfo);
        result.put("message", "模型属性获取成功");

        return ResponseEntity.ok(result);
    }

    /**
     * 9. HttpServletRequest - 原始请求对象
     */
    @GetMapping("/request")
    @Operation(summary = "原始请求对象演示", description = "演示HttpServletRequest的使用")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取请求信息", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"method\":\"GET\",\"requestURI\":\"/api/demo/request\",\"queryString\":\"param=value\"}")))
    })
    public ResponseEntity<Map<String, Object>> httpServletRequestDemo(HttpServletRequest request) {

        Map<String, Object> result = new HashMap<>();
        result.put("method", request.getMethod());
        result.put("requestURI", request.getRequestURI());
        result.put("queryString", request.getQueryString());
        result.put("remoteAddr", request.getRemoteAddr());
        result.put("userAgent", request.getHeader("User-Agent"));
        result.put("message", "原始请求对象获取成功");

        return ResponseEntity.ok(result);
    }

    /**
     * 10. @RequestParam Map - 参数映射
     */
    @GetMapping("/params-map")
    @Operation(summary = "参数映射演示", description = "演示@RequestParam Map的使用")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取所有参数", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"allParams\":{\"name\":\"张三\",\"age\":\"25\"}}")))
    })
    public ResponseEntity<Map<String, Object>> requestParamMapDemo(
            @Parameter(description = "所有查询参数") @RequestParam Map<String, String> allParams) {

        Map<String, Object> result = new HashMap<>();
        result.put("allParams", allParams);
        result.put("paramCount", allParams.size());
        result.put("message", "参数映射获取成功");

        return ResponseEntity.ok(result);
    }

    /**
     * 11. 混合参数演示
     */
    @PostMapping("/mixed/{userId}")
    @Operation(summary = "混合参数演示", description = "演示多种参数获取方式的组合使用")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取混合参数", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"pathVariable\":\"123\",\"queryParam\":\"value\",\"requestBody\":{\"data\":\"test\"}}")))
    })
    public ResponseEntity<Map<String, Object>> mixedParametersDemo(
            @Parameter(description = "用户ID") @PathVariable String userId,
            @Parameter(description = "查询参数") @RequestParam(value = "action", defaultValue = "update") String action,
            @Parameter(description = "请求体数据") @RequestBody Map<String, Object> requestBody,
            @Parameter(description = "授权头") @RequestHeader(value = "Authorization", required = false) String authHeader,
            @Parameter(description = "会话Cookie") @CookieValue(value = "sessionId", required = false) String sessionId) {

        Map<String, Object> result = new HashMap<>();
        result.put("pathVariable", userId);
        result.put("queryParam", action);
        result.put("requestBody", requestBody);
        result.put("authHeader", authHeader);
        result.put("sessionId", sessionId);
        result.put("message", "混合参数获取成功");

        return ResponseEntity.ok(result);
    }

    /**
     * 12. 设置Cookie演示
     */
    @PostMapping("/set-cookie")
    @Operation(summary = "设置Cookie演示", description = "演示如何设置Cookie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功设置Cookie", content = @Content(mediaType = "application/json", examples = @ExampleObject(value = "{\"message\":\"Cookie设置成功\"}")))
    })
    public ResponseEntity<Map<String, Object>> setCookieDemo(
            @Parameter(description = "Cookie名称") @RequestParam String name,
            @Parameter(description = "Cookie值") @RequestParam String value,
            HttpServletResponse response) {

        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(3600); // 1小时过期
        cookie.setPath("/");
        response.addCookie(cookie);

        Map<String, Object> result = new HashMap<>();
        result.put("cookieName", name);
        result.put("cookieValue", value);
        result.put("message", "Cookie设置成功");

        return ResponseEntity.ok(result);
    }
}
