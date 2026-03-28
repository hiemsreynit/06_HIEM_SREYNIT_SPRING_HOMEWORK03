package com.hw.exception.hiem_sreynit_spring_homework03.service;

import com.hw.exception.hiem_sreynit_spring_homework03.model.dto.request.VenueRequest;
import com.hw.exception.hiem_sreynit_spring_homework03.model.entity.Venue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VenueService {
    List<Venue> getAllVenues(Integer page, Integer size);

    Venue getVenueById(Integer venueId);

    void deleteVenueById(Integer venueId);

    Venue updateVenueById(Integer venueId, VenueRequest request);

    Venue createVenue(VenueRequest request);
}
