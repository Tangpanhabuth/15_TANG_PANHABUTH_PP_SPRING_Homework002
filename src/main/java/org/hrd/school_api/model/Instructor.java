package org.hrd.school_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Instructor {
    private Integer instructorId;
    private String instructorName;
    private String email;

}
