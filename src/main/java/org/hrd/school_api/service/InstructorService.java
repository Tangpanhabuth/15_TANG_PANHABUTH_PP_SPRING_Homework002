package org.hrd.school_api.service;

import org.hrd.school_api.dto.request.InstructorRequest;
import org.hrd.school_api.model.Instructor;
import org.hrd.school_api.utils.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InstructorService {

    ResponseEntity<ApiResponse<List<Instructor>>> getAllInstructor(Integer page, Integer size);
    ResponseEntity<ApiResponse<Instructor>> getInstructorById(Integer instructorId);
    ResponseEntity<ApiResponse<Instructor>> addInstructor(InstructorRequest instructorRequest);
    ResponseEntity<ApiResponse<Instructor>> updateInstructor(Integer instructorId ,InstructorRequest instructorRequest);
    ResponseEntity<ApiResponse<Instructor>> deleteInstructor(Integer instructorId);
}
