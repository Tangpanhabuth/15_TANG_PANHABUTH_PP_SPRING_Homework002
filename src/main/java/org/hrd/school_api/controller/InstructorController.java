package org.hrd.school_api.controller;

import lombok.RequiredArgsConstructor;
import org.hrd.school_api.dto.request.InstructorRequest;
import org.hrd.school_api.model.Instructor;
import org.hrd.school_api.service.InstructorService;
import org.hrd.school_api.utils.ApiResponse;
import org.hrd.school_api.utils.BaseEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(BaseEndpoint.url+("/instructors"))
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructor>>> getAllInstructor(@RequestParam (defaultValue = "10") Integer size , @RequestParam (defaultValue = "1") Integer page) {
        return instructorService.getAllInstructor(page,size);
    }


    @GetMapping("/{instructorId}")
    public ResponseEntity<ApiResponse<Instructor>> getInstructorById(@PathVariable Integer instructorId) {
        return instructorService.getInstructorById(instructorId);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Instructor>> addInstructor(@RequestBody InstructorRequest instructorRequest) {
        return instructorService.addInstructor(instructorRequest);
    }

    @PutMapping("/{instructorId}")
    public ResponseEntity<ApiResponse<Instructor>> updateInstructor(@PathVariable Integer instructorId, @RequestBody InstructorRequest instructorRequest) {
        return instructorService.updateInstructor(instructorId, instructorRequest);
    }

    @DeleteMapping("/{instructorId}")
    public ResponseEntity<ApiResponse<Instructor>> deleteInstructor(@PathVariable Integer instructorId) {
        return instructorService.deleteInstructor(instructorId);
    }

}
