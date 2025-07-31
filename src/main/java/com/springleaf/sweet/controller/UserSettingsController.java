package com.springleaf.sweet.controller;

import com.springleaf.sweet.common.Result;
import com.springleaf.sweet.model.dto.UserSettingsDTO;
import com.springleaf.sweet.model.entity.UserSettings;
import com.springleaf.sweet.service.UserSettingsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-settings")
@RequiredArgsConstructor
public class UserSettingsController {
    
    private final UserSettingsService settingsService;
    
    @GetMapping
    public Result<UserSettings> getUserSettings() {
        return settingsService.getUserSettings();
    }
    
    @PutMapping
    public Result<String> updateUserSettings(@Valid @RequestBody UserSettingsDTO settingsDTO) {
        return settingsService.updateUserSettings(settingsDTO);
    }
    
    @PostMapping("/reset")
    public Result<String> resetUserSettings() {
        return settingsService.resetUserSettings();
    }
}