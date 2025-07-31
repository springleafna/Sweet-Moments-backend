package com.springleaf.sweet.controller;

import com.springleaf.sweet.common.Result;
import com.springleaf.sweet.model.dto.CourseRepeatRuleDTO;
import com.springleaf.sweet.model.entity.CourseRepeatRule;
import com.springleaf.sweet.service.CourseRepeatRuleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course-repeat-rule")
@RequiredArgsConstructor
public class CourseRepeatRuleController {
    
    private final CourseRepeatRuleService ruleService;
    
    @PostMapping
    public Result<String> createRule(@Valid @RequestBody CourseRepeatRuleDTO ruleDTO) {
        return ruleService.createRule(ruleDTO);
    }
    
    @GetMapping("/course/{courseId}")
    public Result<List<CourseRepeatRule>> getRulesByCourseId(@PathVariable String courseId) {
        return ruleService.getRulesByCourseId(courseId);
    }
    
    @GetMapping("/{ruleId}")
    public Result<CourseRepeatRule> getRuleById(@PathVariable String ruleId) {
        return ruleService.getRuleById(ruleId);
    }
    
    @PutMapping("/{ruleId}")
    public Result<String> updateRule(@PathVariable String ruleId, 
                                   @Valid @RequestBody CourseRepeatRuleDTO ruleDTO) {
        return ruleService.updateRule(ruleId, ruleDTO);
    }
    
    @PostMapping("/{ruleId}/disable")
    public Result<String> disableRule(@PathVariable String ruleId) {
        return ruleService.disableRule(ruleId);
    }
    
    @PostMapping("/{ruleId}/enable")
    public Result<String> enableRule(@PathVariable String ruleId) {
        return ruleService.enableRule(ruleId);
    }
    
    @DeleteMapping("/{ruleId}")
    public Result<String> deleteRule(@PathVariable String ruleId) {
        return ruleService.deleteRule(ruleId);
    }
}