package com.hw.exception.hiem_sreynit_spring_homework03.exception;

import com.hw.exception.hiem_sreynit_spring_homework03.utils.TimeStampFormatter;
import jakarta.servlet.http.HttpServletRequest;
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
    public ProblemDetail handleDuplicateKeyException(Exception e, HttpServletRequest request) {
        String message = e.getMessage();

        if (e instanceof DuplicateKeyException) {
            message = "Attendee name is already exist.";
        }
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, message);
        problemDetail.setProperty("timestamp", TimeStampFormatter.formatter.format(Instant.now()));
        problemDetail.setType(URI.create("https://localhost:8080/errors/duplicate-date"));
        problemDetail.setTitle("Conflict");
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        return problemDetail;
    }
}

