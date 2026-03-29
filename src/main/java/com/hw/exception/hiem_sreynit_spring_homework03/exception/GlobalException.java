package com.hw.exception.hiem_sreynit_spring_homework03.exception;

import com.hw.exception.hiem_sreynit_spring_homework03.utils.TimeStampFormatter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException(NotFoundException e, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setProperty("timestamp", TimeStampFormatter.formatter.format(Instant.now()));
        problemDetail.setType(URI.create("http://localhost:8080/errors/not-found"));
        problemDetail.setTitle("Not Found");
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        return problemDetail;
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ProblemDetail handleDuplicateKeyException(DuplicateKeyException e, HttpServletRequest request) {
        String detail = "Event name already exists on this date.";

        if (e.getMessage() != null && e.getMessage().contains("uk_event_name_date")) {
            detail = "Event name already exists on this date.";
        }
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, detail);
        problemDetail.setProperty("timestamp", TimeStampFormatter.formatter.format(Instant.now()));
        problemDetail.setType(URI.create("https://localhost:8080/errors/conflict"));
        problemDetail.setTitle("Conflict");
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        return problemDetail;
    }

}