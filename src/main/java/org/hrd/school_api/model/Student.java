package org.hrd.school_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private Integer studentId;
    private String studentName;
    private String email;
    private String phoneNumber;
    private List<Course> course;

}
