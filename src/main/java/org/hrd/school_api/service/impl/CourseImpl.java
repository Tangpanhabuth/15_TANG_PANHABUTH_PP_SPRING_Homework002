package org.hrd.school_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.hrd.school_api.dto.request.CourseRequest;
import org.hrd.school_api.model.Course;
import org.hrd.school_api.model.Instructor;
import org.hrd.school_api.repository.CourseRepo;
import org.hrd.school_api.service.CourseService;
import org.hrd.school_api.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CourseImpl implements CourseService {
    private final CourseRepo courseRepo;

    @Override
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses(Integer page, Integer size) {
        List<Course> course = courseRepo.findAllCourses(page,size);

        if(course != null && !course.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    ApiResponse.<List<Course>>builder()
                            .success(Boolean.TRUE)
                            .status("200 OK")
                            .message("get all course success")
                            .payload(course)
                            .time(Instant.now())
                            .build()
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiResponse.<List<Course>>builder()
                        .success(Boolean.FALSE)
                        .status("404 NOT_FOUND")
                        .message("NO COURSE FOUND")
                        .payload(null)
                        .time(Instant.now())
                        .build());

    }

    @Override
    public ResponseEntity<ApiResponse<Course>> getCourseById(Integer courseId) {
        Course course = courseRepo.findCourseById(courseId);

        if(course != null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    ApiResponse.<Course>builder()
                            .success(Boolean.TRUE)
                            .status("200 OK")
                            .message("get course success")
                            .payload(course)
                            .time(Instant.now())
                            .build()
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiResponse.<Course>builder()
                        .success(Boolean.FALSE)
                        .status("404 NOT_FOUND")
                        .message("NO COURSE FOUND")
                        .payload(null)
                        .time(Instant.now())
                        .build());

    }

    @Override
    public ResponseEntity<ApiResponse<Course>> addCourse(CourseRequest courseRequest) {
        Course course = courseRepo.saveCourse(courseRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<Course>builder()
                        .success(Boolean.TRUE)
                        .status("200 OK")
                        .message("Create Course success")
                        .payload(course)
                        .time(Instant.now())
                        .build()
        );
    }

    @Override
    public ResponseEntity<ApiResponse<Void>> deleteCourse(Integer courseId) {
        int course = courseRepo.removeCourseById(courseId);

        if(course == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.<Void>builder()
                            .success(false)
                            .status("404 NOT FOUND")
                            .message("Course NOT FOUND")
                            .payload(null)
                            .time(Instant.now())
                            .build()
            );
        }
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .status("200 OK")
                        .message("Course deleted successfully")
                        .payload(null)
                        .time(Instant.now())
                        .build()
        );

    }

    @Override
    public ResponseEntity<ApiResponse<Course>> updateCourse(Integer courseId, CourseRequest courseRequest) {

        Course course = courseRepo.updateCourse(courseId,courseRequest);
        if(course == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.<Course>builder()
                            .success(false)
                            .status("404 NOT FOUND")
                            .message("Course NOT FOUND")
                            .payload(null)
                            .time(Instant.now())
                            .build()
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Course>builder()
                        .success(true)
                        .status("200 OK")
                        .message("Course UPDATE successfully")
                        .payload(course)
                        .time(Instant.now())
                        .build()
        );
    }


}
