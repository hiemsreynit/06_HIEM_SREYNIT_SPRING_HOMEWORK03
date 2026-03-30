package com.hw.exception.hiem_sreynit_spring_homework03.service.impl;

import com.hw.exception.hiem_sreynit_spring_homework03.exception.DuplicateKeyException;
import com.hw.exception.hiem_sreynit_spring_homework03.exception.NotFoundException;
import com.hw.exception.hiem_sreynit_spring_homework03.model.dto.request.EventRequest;
import com.hw.exception.hiem_sreynit_spring_homework03.model.entity.Attendee;
import com.hw.exception.hiem_sreynit_spring_homework03.model.entity.Event;
import com.hw.exception.hiem_sreynit_spring_homework03.repository.AttendeeRepository;
import com.hw.exception.hiem_sreynit_spring_homework03.repository.EventAttendeeRepository;
import com.hw.exception.hiem_sreynit_spring_homework03.repository.EventRepository;
import com.hw.exception.hiem_sreynit_spring_homework03.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventAttendeeRepository eventAttendeeRepository;
    private final AttendeeRepository attendeeRepository;

    @Override
    public List<Event> getAllEvents(Integer page, Integer size) {
        int offset = size * (page - 1);
        List<Event> events = eventRepository.getAllEvents(offset, size);

        for (Event event : events) {
            List<Attendee> attendees = eventAttendeeRepository.getAttendeeByEventId(event.getEventId());
            event.setAttendees(attendees);
        }

        return events;
    }

    @Override
    public Event getEventById(Integer eventId) {

        Event event = eventRepository.getEventById(eventId);

        if (event == null) {
            throw new NotFoundException("No event found with ID " + eventId + ".");
        }

        return event;
    }

    @Override
    @Transactional
    public Event creatEvent(EventRequest request) {
        if (Boolean.TRUE.equals(eventRepository.existsByEventNameAndEventDate(request.getEventName(), request.getEventDate()))) {
            throw new DuplicateKeyException("Event name already exists on this date");
        }

        if (request.getAttendeesIds() != null) {
            for (Integer attendeeId : request.getAttendeesIds()) {
                if (attendeeRepository.getAttendeeById(attendeeId) == null) {
                    throw new NotFoundException("No attendee found with ID " + attendeeId + ".");
                }
            }
        }

        if (request.getVenueId() == null) {
            throw new NotFoundException("No venue found with given ID.");
        }

        Event event = eventRepository.createEvent(request);

        if (request.getAttendeesIds() != null) {
            for (Integer attendeeId : request.getAttendeesIds()) {
                eventAttendeeRepository.insertIntoEventAndAttendee(event.getEventId(), attendeeId);
            }
        }

        return eventRepository.getEventById(event.getEventId());
    }

    @Override
    public Event deleteEventById(Integer eventId) {
        Event event = eventRepository.getEventById(eventId);

        if (event == null) {
            throw new NotFoundException("No event found with ID " + eventId + ".");
        }

        return eventRepository.deleteEventById(eventId);
    }

    @Override
    public Event updateEventById(Integer eventId, EventRequest request) {
        for (Integer attendeeId : request.getAttendeesIds()) {
            if (attendeeId == null) {
                throw new NotFoundException("No venue found with given ID.");
            }
        }

        if (request.getVenueId() == null) {
            throw new NotFoundException("No venue found with given ID.");
        }

        if (eventRepository.getEventById(eventId) == null) {
            throw new NotFoundException("No event found with ID " + eventId + ".");
        }

        eventAttendeeRepository.deleteAttendeeByEventId(eventId);

        for (Integer attendeeId : request.getAttendeesIds()) {
            eventAttendeeRepository.insertIntoEventAndAttendee(eventId, attendeeId);
        }

        Event event = eventRepository.updateEventById(eventId, request);

        return eventRepository.getEventById(event.getEventId());
    }
}
