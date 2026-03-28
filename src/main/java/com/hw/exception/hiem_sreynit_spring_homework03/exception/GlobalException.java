package com.hw.exception.hiem_sreynit_spring_homework03.exception;

import com.hw.exception.hiem_sreynit_spring_homework03.utils.TimeStampFormatter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException(NotFoundException e, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setProperty("timestamp", TimeStampFormatter.formatter.format(LocalDateTime.now()));
        problemDetail.setType(URI.create("http://localhost:8080/errors/not-found"));
        problemDetail.setTitle("Not Found");
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        return problemDetail;
    }

}