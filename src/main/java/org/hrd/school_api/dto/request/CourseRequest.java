package org.hrd.school_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hrd.school_api.model.Instructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {

    private String courseName;
    private String description;
    private Integer instructorId;
}
