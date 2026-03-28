package com.hw.exception.hiem_sreynit_spring_homework03.controller;

import com.hw.exception.hiem_sreynit_spring_homework03.model.dto.response.ApiResponse;
import com.hw.exception.hiem_sreynit_spring_homework03.model.entity.Venue;
import com.hw.exception.hiem_sreynit_spring_homework03.service.VenueService;
import com.hw.exception.hiem_sreynit_spring_homework03.service.impl.VenueServiceImpl;
import com.hw.exception.hiem_sreynit_spring_homework03.utils.TimeStampFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("api/v1/venues")
@RequiredArgsConstructor
public class VenueController {

    private final VenueService venueService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Venue>>> getAllVenues (
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        List<Venue> venues = venueService.getAllVenues(page, size);
        return ResponseEntity.ok(ApiResponse.<List<Venue>>builder().
                success(true).
                status(HttpStatus.OK).
                message("Venues retrieved successfully.").
                payload(venues).
                timeStamp(TimeStampFormatter.formatter.format(Instant.now())).build());
    }

    @GetMapping("/{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> getVenueById (
            @PathVariable("venue-id") Integer venueId
    ) {
        Venue venue = venueService.getVenueById(venueId);

        if (!(venue == null)) {
            return ResponseEntity.ok(ApiResponse.<Venue>builder().
                    success(true).
                    status(HttpStatus.OK).
                    message("Venue with ID " + venueId + " retrieved successfully.").
                    payload(venue).
                    timeStamp(TimeStampFormatter.formatter.format(Instant.now())).build()
            );
        } else {
            return ResponseEntity.ok(ApiResponse.<Venue>builder().
                    success(false).
                    status(HttpStatus.NOT_FOUND).
                    message("No Venue found with ID " + venueId).
                    timeStamp(TimeStampFormatter.formatter.format(Instant.now())).build()
            );
        }
    }
}
