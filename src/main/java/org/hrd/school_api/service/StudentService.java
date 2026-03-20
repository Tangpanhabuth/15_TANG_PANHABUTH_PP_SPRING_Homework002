package org.hrd.school_api.service;

import org.hrd.school_api.dto.request.StudentRequest;
import org.hrd.school_api.model.Student;
import org.hrd.school_api.utils.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    ResponseEntity<ApiResponse<List<Student>>> getAllStudents(Integer page, Integer size);
    ResponseEntity<ApiResponse<Student>> getStudentById(Integer studentId);

    ResponseEntity<ApiResponse<Void>> deleteStudentById(Integer studentId);

    ResponseEntity<ApiResponse<Student>> createStudent(StudentRequest studentRequest  );

    ResponseEntity<ApiResponse<Student>> updateStudent(Integer studentId, StudentRequest studentRequest);
}
