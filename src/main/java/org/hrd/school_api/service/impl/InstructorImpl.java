package org.hrd.school_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.hrd.school_api.dto.request.InstructorRequest;
import org.hrd.school_api.model.Instructor;
import org.hrd.school_api.model.Student;
import org.hrd.school_api.repository.InstructorRepo;
import org.hrd.school_api.service.InstructorService;
import org.hrd.school_api.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.util.List;
@Service
@RequiredArgsConstructor
public class InstructorImpl implements InstructorService {
    private final InstructorRepo instructorRepo;

    @Override
    public ResponseEntity<ApiResponse<List<Instructor>>> getAllInstructor(Integer page, Integer size) {

        List<Instructor> instructor = instructorRepo.findAllInstructors(page, size);

        if(instructor != null && !instructor.isEmpty()){

            return ResponseEntity.status(HttpStatus.OK).body(
                    ApiResponse.<List<Instructor>>builder()
                            .success(Boolean.TRUE)
                            .status("200 OK")
                            .message("get all Instructor success")
                            .payload(instructor)
                            .time(Instant.now())
                            .build()

            );
        }return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiResponse.<List<Instructor>>builder()
                        .success(Boolean.FALSE)
                        .status("404 NOT_FOUND")
                        .message("NO INSTRUCTOR FOUND")
                        .payload(null)
                        .time(Instant.now())
                        .build()

        );

    }

    @Override
    public ResponseEntity<ApiResponse<Instructor>> getInstructorById(Integer instructorId) {
        Instructor instructor = instructorRepo.findInstructorById(instructorId);

        if(instructor == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.<Instructor>builder()
                            .success(Boolean.FALSE)
                            .status("404 NOT_FOUND")
                            .message("NO STUDENT FOUND")
                            .payload(null)
                            .time(Instant.now())
                            .build()
            );
        }return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Instructor>builder()
                        .success(Boolean.TRUE)
                        .status("200 OK")
                        .message("INSTRUCTOR ID "+instructorId+" FOUND")
                        .payload(instructor)
                        .time(Instant.now())
                        .build()

        );
    }



    @Override
    public ResponseEntity<ApiResponse<Instructor>> addInstructor(InstructorRequest instructorRequest) {

        Instructor instructor = instructorRepo.saveInstructor(instructorRequest);


        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<Instructor>builder()
                        .success(true)
                        .status("201 CREATED")
                        .message("Instructor created successfully")
                        .payload(instructor)
                        .time(Instant.now())
                        .build()
        );
    }

    @Override
    public ResponseEntity<ApiResponse<Instructor>> updateInstructor(Integer instructorId ,InstructorRequest instructorRequest) {
        Instructor instructor = instructorRepo.updateInstructor(instructorId ,instructorRequest);

        if(instructor == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.<Instructor>builder()
                            .success(false)
                            .status("404 NOT FOUND")
                            .message("Instructor NOT FOUND")
                            .payload(null)
                            .time(Instant.now())
                            .build()
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Instructor>builder()
                        .success(true)
                        .status("200 OK")
                        .message("Instructor UPDATE successfully")
                        .payload(instructor)
                        .time(Instant.now())
                        .build()
        );
    }

    @Override
    public ResponseEntity<ApiResponse<Instructor>> deleteInstructor(Integer instructorId) {
        Instructor instructor = instructorRepo.removeInstructor(instructorId);

        if(instructor == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    ApiResponse.<Instructor>builder()
                            .success(false)
                            .status("404 NOT FOUND")
                            .message("Instructor NOT FOUND")
                            .payload(null)
                            .time(Instant.now())
                            .build()
            );
        }
        return ResponseEntity.ok(
                ApiResponse.<Instructor>builder()
                        .success(true)
                        .status("200 OK")
                        .message("Instructor deleted successfully")
                        .payload(null)
                        .time(Instant.now())
                        .build()
        );


    }


}
