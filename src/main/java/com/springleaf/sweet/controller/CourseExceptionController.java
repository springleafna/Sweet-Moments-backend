package com.springleaf.sweet.controller;

import com.springleaf.sweet.common.Result;
import com.springleaf.sweet.model.dto.CourseExceptionDTO;
import com.springleaf.sweet.model.entity.CourseException;
import com.springleaf.sweet.service.CourseExceptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course-exception")
@RequiredArgsConstructor
public class CourseExceptionController {
    
    private final CourseExceptionService exceptionService;
    
    @PostMapping
    public Result<String> createException(@Valid @RequestBody CourseExceptionDTO exceptionDTO) {
        return exceptionService.createException(exceptionDTO);
    }
    
    @GetMapping("/rule/{ruleId}")
    public Result<List<CourseException>> getExceptionsByRuleId(@PathVariable String ruleId) {
        return exceptionService.getExceptionsByRuleId(ruleId);
    }
    
    @GetMapping("/{exceptionId}")
    public Result<CourseException> getExceptionById(@PathVariable String exceptionId) {
        return exceptionService.getExceptionById(exceptionId);
    }
    
    @PutMapping("/{exceptionId}")
    public Result<String> updateException(@PathVariable String exceptionId,
                                        @Valid @RequestBody CourseExceptionDTO exceptionDTO) {
        return exceptionService.updateException(exceptionId, exceptionDTO);
    }
    
    @DeleteMapping("/{exceptionId}")
    public Result<String> deleteException(@PathVariable String exceptionId) {
        return exceptionService.deleteException(exceptionId);
    }
}