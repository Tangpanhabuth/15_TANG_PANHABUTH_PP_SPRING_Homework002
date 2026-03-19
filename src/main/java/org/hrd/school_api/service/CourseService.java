package org.hrd.school_api.service;

import org.hrd.school_api.dto.request.CourseRequest;
import org.hrd.school_api.model.Course;
import org.hrd.school_api.model.Instructor;
import org.hrd.school_api.utils.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {

    ResponseEntity<ApiResponse<List<Course>>> getAllCourses(Integer page, Integer size);
    ResponseEntity<ApiResponse<Course>> getCourseById(Integer courseId);
    ResponseEntity<ApiResponse<Course>> addCourse(CourseRequest courseRequest);
    ResponseEntity<ApiResponse<Void>> deleteCourse(Integer courseId);
    ResponseEntity<ApiResponse<Course>> updateCourse(Integer courseId ,CourseRequest courseRequest);
}
