package org.hrd.school_api.controller;

import lombok.RequiredArgsConstructor;
import org.hrd.school_api.dto.request.CourseRequest;
import org.hrd.school_api.model.Course;
import org.hrd.school_api.service.CourseService;
import org.hrd.school_api.utils.ApiResponse;
import org.hrd.school_api.utils.BaseEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(BaseEndpoint.url+("/courses"))
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses(@RequestParam (defaultValue = "1") Integer page,@RequestParam (defaultValue = "10") Integer size) {
        return courseService.getAllCourses(page, size);
    }
    @GetMapping("/{courseId}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(@PathVariable Integer courseId) {
        return courseService.getCourseById(courseId);
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<Course>> addCourse(@RequestBody CourseRequest courseRequest) {
        return courseService.addCourse(courseRequest);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<ApiResponse<Void>> deleteCourse(@PathVariable Integer courseId) {
        return courseService.deleteCourse(courseId);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<ApiResponse<Course>> updateCourse(@PathVariable Integer courseId, @RequestBody CourseRequest courseRequest) {
        return courseService.updateCourse(courseId, courseRequest);
    }




}
