package com.mycompany.exceptions;

import com.mycompany.exceptions.inputData.InputDataException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestControllerAdvice
public class ExceptionsHandler {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> invalidData(final InputDataException e) {
        log.info("Не верно заполнена строка", e.getMessage());
        String exceptionName = e.getClass().getName();
        return new ResponseEntity<>(new ExceptionResponse(exceptionName, e.getMessage(),
                "BAD_REQUEST", LocalDateTime.now().format(formatter)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> invalidData(final ConstraintViolationException e) {
        log.info("Длина строки вне диапазона.", e.getMessage());
        String exceptionName = e.getClass().getName();
        return new ResponseEntity<>(new ExceptionResponse(exceptionName, e.getMessage(),
                "BAD_REQUEST", LocalDateTime.now().format(formatter)), HttpStatus.BAD_REQUEST);
    }

    @Getter
    @AllArgsConstructor
    static class ExceptionResponse {
        private final String exception;
        private final String message;
        private final String status;
        private final String timestamp;
    }
}
