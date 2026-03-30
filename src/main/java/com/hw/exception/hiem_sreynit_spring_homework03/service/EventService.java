package com.hw.exception.hiem_sreynit_spring_homework03.service;

import com.hw.exception.hiem_sreynit_spring_homework03.model.dto.request.EventRequest;
import com.hw.exception.hiem_sreynit_spring_homework03.model.entity.Event;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {
    List<Event> getAllEvents(Integer page, Integer size);

    Event getEventById(Integer eventId);

    Event creatEvent(EventRequest request);

    Event deleteEventById(Integer eventId);

    Event updateEventById(Integer eventId, EventRequest request);
}
