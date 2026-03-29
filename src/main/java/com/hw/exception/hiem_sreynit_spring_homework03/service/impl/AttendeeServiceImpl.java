package com.hw.exception.hiem_sreynit_spring_homework03.service.impl;


import com.hw.exception.hiem_sreynit_spring_homework03.exception.NotFoundException;
import com.hw.exception.hiem_sreynit_spring_homework03.model.dto.request.AttendeeRequest;
import com.hw.exception.hiem_sreynit_spring_homework03.model.entity.Attendee;
import com.hw.exception.hiem_sreynit_spring_homework03.repository.AttendeeRepository;
import com.hw.exception.hiem_sreynit_spring_homework03.service.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {

    private final AttendeeRepository attendeeRepository;

    @Override
    public List<Attendee> getAllAttendees(Integer page, Integer size) {

        int offset = size * (page - 1);
        List<Attendee> attendees = attendeeRepository.getAllAttendees(offset, size);

        if (attendees == null) {
            throw new NotFoundException("Attendees have no data.");
        }

        return attendees;
    }

    @Override
    public Attendee getAttendeeById(Integer attendeeId) {
        Attendee attendee = attendeeRepository.getAttendeeById(attendeeId);

        if (attendee == null) {
            throw new NotFoundException("No attendee found with ID " + attendeeId + ".");
        }

        return attendee;
    }

    @Override
    public Attendee createAttendee(AttendeeRequest request) {
        return attendeeRepository.createAttendee(request);
    }

    @Override
    public Attendee deleteAttendeeById(Integer attendeeId) {
        Attendee attendee = attendeeRepository.getAttendeeById(attendeeId);

        if (attendee == null) {
            throw new NotFoundException("No Attendee with ID " + attendeeId + " found.");
        }

        return attendeeRepository.deleteAttendeeById(attendeeId);
    }

    @Override
    public Attendee updateAttendeeById(Integer attendeeId, AttendeeRequest request) {
        Attendee attendee = attendeeRepository.getAttendeeById(attendeeId);

        if (attendee == null) {
            throw new NotFoundException("No Attendee with ID " + attendeeId + " found.");
        }

        attendeeRepository.updateAttendeeById(attendeeId, request);

        return attendeeRepository.getAttendeeById(attendee.getAttendeeId());
    }
}
