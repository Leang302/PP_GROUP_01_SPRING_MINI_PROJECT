package com.leang.springminiproject.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse <T>{
    private Boolean success;
    private String message;
    private HttpStatus status;
    private T payload;
    private Instant timestamps;
}
