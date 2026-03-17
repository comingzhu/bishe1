package com.itheima.controller;

import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginInfo loginInfo) {
        log.info("用户登录：{}", loginInfo.getUsername());
        
        User user = userService.login(loginInfo.getUsername(), loginInfo.getPassword());
        
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        claims.put("role", user.getRole());
        
        String token = JwtUtils.generateToken(claims);
        
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("user", user);
        
        return Result.success(data);
    }

    @GetMapping("/info")
    public Result getInfo(@RequestHeader("token") String token) {
        try {
            Claims claims = JwtUtils.parseToken(token);
            Integer userId = Integer.valueOf(claims.get("id").toString());
            User user = userService.findById(userId);
            return Result.success(user);
        } catch (Exception e) {
            return Result.error("token无效或已过期");
        }
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        log.info("用户注册：{}", user.getUsername());
        log.info("注册数据：username={}, password={}, realName={}, role={}, email={}, phone={}, studentId={}, workId={}", 
            user.getUsername(), user.getPassword(), user.getRealName(), user.getRole(), 
            user.getEmail(), user.getPhone(), user.getStudentId(), user.getWorkId());
        
        User existUser = userService.findByUsername(user.getUsername());
        if (existUser != null) {
            return Result.error("用户名已存在");
        }
        
        userService.register(user);
        return Result.success("注册成功");
    }
}
