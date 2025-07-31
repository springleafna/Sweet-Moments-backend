package com.springleaf.sweet.controller;

import com.springleaf.sweet.common.Result;
import com.springleaf.sweet.model.dto.CourseCreateDTO;
import com.springleaf.sweet.model.entity.Course;
import com.springleaf.sweet.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {
    
    private final CourseService courseService;
    
    @PostMapping
    public Result<String> createCourse(@Valid @RequestBody CourseCreateDTO createDTO) {
        return courseService.createCourse(createDTO);
    }
    
    @GetMapping("/list")
    public Result<List<Course>> getUserCourses() {
        return courseService.getUserCourses();
    }
    
    @GetMapping("/list/time-range")
    public Result<List<Course>> getUserCoursesByTimeRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
        return courseService.getUserCoursesByTimeRange(startTime, endTime);
    }
    
    @GetMapping("/list/type/{courseType}")
    public Result<List<Course>> getUserCoursesByType(@PathVariable String courseType) {
        return courseService.getUserCoursesByType(courseType);
    }
    
    @GetMapping("/{courseId}")
    public Result<Course> getCourseById(@PathVariable String courseId) {
        return courseService.getCourseById(courseId);
    }
    
    @PutMapping("/{courseId}")
    public Result<String> updateCourse(@PathVariable String courseId, 
                                     @Valid @RequestBody CourseCreateDTO updateDTO) {
        return courseService.updateCourse(courseId, updateDTO);
    }
    
    @PostMapping("/{courseId}/cancel")
    public Result<String> cancelCourse(@PathVariable String courseId) {
        return courseService.cancelCourse(courseId);
    }
    
    @PostMapping("/{courseId}/complete")
    public Result<String> completeCourse(@PathVariable String courseId) {
        return courseService.completeCourse(courseId);
    }
    
    @DeleteMapping("/{courseId}")
    public Result<String> deleteCourse(@PathVariable String courseId) {
        return courseService.deleteCourse(courseId);
    }
}