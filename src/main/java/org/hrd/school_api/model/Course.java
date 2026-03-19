package org.hrd.school_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Course {

    private Integer courseId;
    private String courseName;
    private String description;
    private Instructor instructor;

}
