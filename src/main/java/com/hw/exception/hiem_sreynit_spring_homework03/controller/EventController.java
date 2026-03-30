package com.hw.exception.hiem_sreynit_spring_homework03.controller;

import com.hw.exception.hiem_sreynit_spring_homework03.model.dto.request.EventRequest;
import com.hw.exception.hiem_sreynit_spring_homework03.model.dto.response.ApiResponse;
import com.hw.exception.hiem_sreynit_spring_homework03.model.entity.Event;
import com.hw.exception.hiem_sreynit_spring_homework03.service.EventService;
import com.hw.exception.hiem_sreynit_spring_homework03.utils.TimeStampFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Event>>> getAllEvents(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        List<Event> events = eventService.getAllEvents(page, size);

        return ResponseEntity.ok(ApiResponse.<List<Event>>builder().
                success(true).
                status(HttpStatus.OK).
                message("Events retrieved successfully.").
                payload(events).
                timeStamp(TimeStampFormatter.formatter.format(Instant.now())).build()
        );
    }

    @GetMapping("/{event-id}")
    public ResponseEntity<ApiResponse<Event>> getEventById(
            @PathVariable("event-id") Integer eventId
    ) {
        Event event = eventService.getEventById(eventId);

        return ResponseEntity.ok(ApiResponse.<Event>builder().
                success(true).
                status(HttpStatus.OK).
                message("Event with ID " + eventId + " retrieved successfully.").
                payload(event).
                timeStamp(TimeStampFormatter.formatter.format(Instant.now())).build()
        );

    }

    @PostMapping
    public ResponseEntity<ApiResponse<Event>> createEvent(
            @RequestBody EventRequest request
    ) {
        Event event = eventService.creatEvent(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<Event>builder().
                success(true).
                status(HttpStatus.CREATED).
                message("Event created successfully.").
                payload(event).
                timeStamp(TimeStampFormatter.formatter.format(Instant.now())).build()
        );
    }

    @DeleteMapping("/{event-id}")
    public ResponseEntity<ApiResponse<Event>> deleteEventById (
            @PathVariable("event-id") Integer eventId
    ) {
        Event event = eventService.deleteEventById(eventId);

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.<Event>builder().
                status(HttpStatus.OK).
                message("Event ID " + event + " deleted successfully.").
                timeStamp(TimeStampFormatter.formatter.format(Instant.now())).build()
        );
    }

    @PutMapping("/{event-id}")
    public ResponseEntity<ApiResponse<Event>> updateEventById (
            @PathVariable("event-id") Integer eventId,
            @RequestBody EventRequest request
    ) {
        Event event = eventService.updateEventById(eventId, request);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ApiResponse.<Event>builder().
                success(true).
                status(HttpStatus.ACCEPTED).
                message("Event created successfully.").
                payload(event).
                timeStamp(TimeStampFormatter.formatter.format(Instant.now())).build()
        );
    }
}
