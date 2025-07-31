package com.springleaf.sweet.controller;

import com.springleaf.sweet.common.Result;
import com.springleaf.sweet.model.dto.UserLoginDTO;
import com.springleaf.sweet.model.dto.UserRegisterDTO;
import com.springleaf.sweet.model.dto.UserUpdateDTO;
import com.springleaf.sweet.model.entity.User;
import com.springleaf.sweet.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@Valid @RequestBody UserLoginDTO loginDTO) {
        return userService.login(loginDTO);
    }
    
    @PostMapping("/register")
    public Result<String> register(@Valid @RequestBody UserRegisterDTO registerDTO) {
        return userService.register(registerDTO);
    }
    
    @PostMapping("/logout")
    public Result<String> logout() {
        return userService.logout();
    }
    
    @GetMapping("/info")
    public Result<User> getUserInfo() {
        return userService.getUserInfo();
    }
    
    @PutMapping("/info")
    public Result<String> updateUserInfo(@Valid @RequestBody UserUpdateDTO updateDTO) {
        return userService.updateUserInfo(updateDTO);
    }
    
    @PostMapping("/bind-partner")
    public Result<String> bindPartner(@RequestParam String partnerEmail) {
        return userService.bindPartner(partnerEmail);
    }
}