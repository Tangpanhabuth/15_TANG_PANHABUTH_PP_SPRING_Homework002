package org.hrd.school_api.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.Instant;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ApiResponse <t>{
    private Boolean success;
    private String status;
    private String message;
    private t payload;
    private Instant time;
}
