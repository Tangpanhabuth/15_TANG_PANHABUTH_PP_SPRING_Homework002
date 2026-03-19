package org.hrd.school_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {

    private String studentName;
    private String email;
    private String phoneNumber;
    private List<Integer> courseIds;

}
