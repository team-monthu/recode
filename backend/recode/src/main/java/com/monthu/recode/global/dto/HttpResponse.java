package com.monthu.recode.global.dto;

import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpResponse {

    public static ResponseEntity<?> ok(HttpStatus status, String message) {
        SuccessResponseDto responseDto = new SuccessResponseDto(message, new ArrayList<>());
        return ResponseEntity.status(status.value())
                             .body(responseDto);
    }

    public static ResponseEntity<?> okWithData(HttpStatus status, String message, Object data) {
        SuccessResponseDto responseDto = new SuccessResponseDto(message, data);
        return ResponseEntity.status(status.value())
                             .body(responseDto);
    }

    public static ResponseEntity<?> fail(HttpStatus status, String message) {
        FailResponseDto responseDto = new FailResponseDto(message);
        return ResponseEntity.status(status.value())
                             .body(responseDto);
    }
}
