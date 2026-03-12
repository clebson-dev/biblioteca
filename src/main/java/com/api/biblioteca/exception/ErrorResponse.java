package com.api.biblioteca.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    private List<ValidationError> validationErrors;

    @Data
    @AllArgsConstructor
    public static class ValidationError {
        private String field;
        private String defaultMessage;
    }
}
