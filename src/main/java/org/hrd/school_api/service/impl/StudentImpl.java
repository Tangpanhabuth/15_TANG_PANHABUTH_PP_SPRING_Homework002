package org.hrd.school_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.hrd.school_api.dto.request.StudentRequest;
import org.hrd.school_api.model.Student;
import org.hrd.school_api.repository.StudentCourseRepository;
import org.hrd.school_api.repository.StudentRepo;
import org.hrd.school_api.service.StudentService;
import org.hrd.school_api.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class StudentImpl implements StudentService {

    private final StudentRepo studentRepo;
    private final StudentCourseRepository studentCourseRepo;

    @Override
    public ResponseEntity<ApiResponse<List<Student>>> getAllStudents(Integer page, Integer size) {

        List<Student> stu = studentRepo.findAllStudents(page,size);

        if(stu != null && !stu.isEmpty()){

            return ResponseEntity.status(HttpStatus.OK).body(
                    ApiResponse.<List<Student>>builder()
                            .success(Boolean.TRUE)
                            .status("200 OK")
                            .message("get all students success")
                            .payload(stu)
                            .time(Instant.now())
                            .build()

            );
        }return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiResponse.<List<Student>>builder()
                        .success(Boolean.FALSE)
                        .status("404 NOT_FOUND")
                        .message("NO STUDENT FOUND")
                        .payload(null)
                        .time(Instant.now())
                        .build()

        );

    }

    @Override
    public ResponseEntity<ApiResponse<Student>> getStudentById(Integer studentId) {

        Student stu = studentRepo.findStudentById(studentId);

        if(stu == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.<Student>builder()
                            .success(Boolean.FALSE)
                            .status("404 NOT_FOUND")
                            .message("NO STUDENT FOUND")
                            .payload(null)
                            .time(Instant.now())
                            .build()
            );
        }return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Student>builder()
                        .success(Boolean.TRUE)
                        .status("200 OK")
                        .message("STUDENT ID "+studentId+" FOUND")
                        .payload(stu)
                        .time(Instant.now())
                        .build()

        );
    }

    @Override
    public ResponseEntity<ApiResponse<Void>> deleteStudentById(Integer studentId) {
        int student = studentRepo.removeStudentById(studentId);
        if(student == 0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.<Void>builder()
                            .success(false)
                            .status("404 NOT FOUND")
                            .message("student NOT FOUND")
                            .payload(null)
                            .time(Instant.now())
                            .build()
            );
        }
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .status("200 OK")
                        .message("student deleted successfully")
                        .payload(null)
                        .time(Instant.now())
                        .build()
        );
    }

    @Override
    public ResponseEntity<ApiResponse<Student>> createStudent(StudentRequest studentRequest) {

        Student student = studentRepo.createStudent(studentRequest);

        if (studentRequest.getCourseIds() != null) {
            for (Integer courseId : studentRequest.getCourseIds()) {
                studentCourseRepo.addStudentToCourse(student.getStudentId(), courseId);
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<Student>builder()
                        .success(true)
                        .status("201 CREATED")
                        .message("Student created successfully")
                        .payload(studentRepo.findStudentById(student.getStudentId()))
                        .time(Instant.now())
                        .build()
        );
    }

    @Override
    public ResponseEntity<ApiResponse<Student>> updateStudent(Integer studentId, StudentRequest studentRequest) {

        Student student = studentRepo.findStudentById(studentId);
        if(student == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.<Student>builder()
                            .success(false)
                            .status("404 NOT FOUND")
                            .message("NOT FOUND")
                            .payload(null)
                            .time(Instant.now())
                            .build()
            );
        }
        Student updatedStudent = studentRepo.updateStudent(studentId, studentRequest);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Student>builder()
                        .success(true)
                        .status("201 CREATED")
                        .message("Student Update Student - " +student.getStudentId() +" successfully")
                        .payload(updatedStudent)
                        .time(Instant.now())
                        .build()
        );
    }


}
