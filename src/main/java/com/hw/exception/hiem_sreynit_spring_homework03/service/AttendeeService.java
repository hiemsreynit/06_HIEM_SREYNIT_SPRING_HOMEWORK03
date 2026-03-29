package com.hw.exception.hiem_sreynit_spring_homework03.service;

import com.hw.exception.hiem_sreynit_spring_homework03.model.dto.request.AttendeeRequest;
import com.hw.exception.hiem_sreynit_spring_homework03.model.entity.Attendee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AttendeeService {
    List<Attendee> getAllAttendees(Integer page, Integer size);

    Attendee getAttendeeById(Integer attendeeId);

    Attendee createAttendee(AttendeeRequest request);

    Attendee deleteAttendeeById(Integer attendeeId);

    Attendee updateAttendeeById(Integer attendeeId, AttendeeRequest request);
}
