package com.hw.exception.hiem_sreynit_spring_homework03.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    private Boolean success;
    private HttpStatus status;
    private String message;
    private T payload;
    private String timeStamp;
}
