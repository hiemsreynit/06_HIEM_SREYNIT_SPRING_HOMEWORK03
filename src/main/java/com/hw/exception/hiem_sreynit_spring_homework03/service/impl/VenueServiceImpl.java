package com.hw.exception.hiem_sreynit_spring_homework03.service.impl;

import com.hw.exception.hiem_sreynit_spring_homework03.exception.NotFoundException;
import com.hw.exception.hiem_sreynit_spring_homework03.model.entity.Venue;
import com.hw.exception.hiem_sreynit_spring_homework03.repository.VenueRepository;
import com.hw.exception.hiem_sreynit_spring_homework03.service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;

    @Override
    public List<Venue> getAllVenues(Integer page, Integer size) {
        int offset = size * (page - 1);
        List<Venue> venue = venueRepository.getAllVenues(offset, size);

        if (venue == null) {
            throw new NotFoundException("No data for venue.");
        }

        return venue;
    }

    @Override
    public Venue getVenueById(Integer venueId) {
        Venue venue = venueRepository.getVenueById(venueId);

        if (venue == null) {
            throw new NotFoundException("No venue found with ID " + venueId);
        }
        return venue;
    }

    @Override
    public void deleteVenueById(Integer venueId) {
        Venue venue = venueRepository.getVenueById(venueId);

        if (venue == null) {
            throw new NotFoundException("No venue found with ID " + venueId);
        }

        venueRepository.deleteVenueById(venueId);

    }
}
