package org.hrd.school_api.controller;

import lombok.RequiredArgsConstructor;
import org.hrd.school_api.dto.request.StudentRequest;
import org.hrd.school_api.model.Student;
import org.hrd.school_api.service.StudentService;
import org.hrd.school_api.service.impl.StudentImpl;
import org.hrd.school_api.utils.ApiResponse;
import org.hrd.school_api.utils.BaseEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(BaseEndpoint.url+("/students"))
@RequiredArgsConstructor
public class StudentController {

    private final StudentImpl studentImp;

    @GetMapping
    ResponseEntity<ApiResponse<List<Student>>> getAllStudents(@RequestParam (defaultValue = "1") Integer page,@RequestParam (defaultValue = "10") Integer size) {
        return studentImp.getAllStudents(page ,size);
    }

    @GetMapping("/{studentId}")
    ResponseEntity<ApiResponse<Student>> getStudentById(@PathVariable Integer studentId) {
        return studentImp.getStudentById(studentId);
    }

    @DeleteMapping("/{studentId}")
    ResponseEntity<ApiResponse<Void>> deleteStudentById(@PathVariable Integer studentId) {
        return  studentImp.deleteStudentById(studentId);
    }


    @PostMapping()
    ResponseEntity<ApiResponse<Student>> createStudent(@RequestBody StudentRequest studentRequest) {
        return studentImp.createStudent(studentRequest);
    }

    @PutMapping("/{studentId}")
    ResponseEntity<ApiResponse<Student>> updateStudent(@PathVariable Integer studentId, @RequestBody StudentRequest studentRequest) {
        return studentImp.updateStudent(studentId, studentRequest);
    }



}


