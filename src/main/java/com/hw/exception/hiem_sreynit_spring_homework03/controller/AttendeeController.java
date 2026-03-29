package com.hw.exception.hiem_sreynit_spring_homework03.controller;

import com.hw.exception.hiem_sreynit_spring_homework03.model.dto.request.AttendeeRequest;
import com.hw.exception.hiem_sreynit_spring_homework03.model.dto.response.ApiResponse;
import com.hw.exception.hiem_sreynit_spring_homework03.model.entity.Attendee;
import com.hw.exception.hiem_sreynit_spring_homework03.service.AttendeeService;
import com.hw.exception.hiem_sreynit_spring_homework03.utils.TimeStampFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("api/v1/attendees")
@RequiredArgsConstructor
public class AttendeeController {

    private final AttendeeService attendeeService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Attendee>>> getAllAttendees(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        List<Attendee> attendees = attendeeService.getAllAttendees(page, size);

        return ResponseEntity.ok(ApiResponse.<List<Attendee>>builder().
                success(true).
                status(HttpStatus.OK).
                message("Attendees retrieved successfully.").
                payload(attendees).
                timeStamp(TimeStampFormatter.formatter.format(Instant.now())).build()
        );
    }

    @GetMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> getAttendeeById(
            @PathVariable("attendee-id") Integer attendeeId
    ) {
        Attendee attendee = attendeeService.getAttendeeById(attendeeId);

        return ResponseEntity.ok(ApiResponse.<Attendee>builder().
                success(true).
                status(HttpStatus.OK).
                message("Attendee with ID " + attendeeId + " retrieved successfully.").
                payload(attendee).
                timeStamp(TimeStampFormatter.formatter.format(Instant.now())).build()
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Attendee>> createAttendee(
            @RequestBody AttendeeRequest request
    ) {
        Attendee attendee = attendeeService.createAttendee(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<Attendee>builder().
                success(true).
                status(HttpStatus.OK).
                message("New Attendee created successfully.").
                payload(attendee).
                timeStamp(TimeStampFormatter.formatter.format(Instant.now())).build()
        );
    }

    @DeleteMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> deleteAttendeeById (
            @PathVariable("attendee-id") Integer attendeeId
    ) {
        attendeeService.deleteAttendeeById(attendeeId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponse.<Attendee>builder().
                success(true).
                status(HttpStatus.NO_CONTENT).
                message("Attendee with ID " + attendeeId + " deleted successfully.").
                timeStamp(TimeStampFormatter.formatter.format(Instant.now())).build()
        );
    }

    @PutMapping("/{attendee-id}")
    public ResponseEntity<ApiResponse<Attendee>> updateAttendeeById (
            @PathVariable("attendee-id") Integer attendeeId,
            @RequestBody AttendeeRequest request
    ) {
        Attendee attendee = attendeeService.updateAttendeeById(attendeeId, request);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponse.<Attendee>builder().
                success(true).
                status(HttpStatus.ACCEPTED).
                payload(attendee).
                message("Attendee with ID " + attendeeId + " updated successfully.").
                timeStamp(TimeStampFormatter.formatter.format(Instant.now())).build()
        );
    }
}
