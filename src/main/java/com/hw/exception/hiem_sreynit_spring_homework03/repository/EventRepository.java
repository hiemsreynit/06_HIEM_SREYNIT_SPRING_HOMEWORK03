package com.hw.exception.hiem_sreynit_spring_homework03.repository;

import com.hw.exception.hiem_sreynit_spring_homework03.model.dto.request.EventRequest;
import com.hw.exception.hiem_sreynit_spring_homework03.model.entity.Event;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EventRepository {

    @Results(id = "eventMapper", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venue", column = "venue_id",
                    one = @One(select = "com.hw.exception.hiem_sreynit_spring_homework03.repository.VenueRepository.getVenueById")),
            @Result(property = "attendees", column = "event_id",
                    many = @Many(select = "com.hw.exception.hiem_sreynit_spring_homework03.repository.EventAttendeeRepository.getAttendeeByEventId"))

    })

    @Select("""
                SELECT * FROM events OFFSET #{offset} LIMIT #{size};
            """)
    List<Event> getAllEvents(int offset, Integer size);


    @ResultMap("eventMapper")
    @Select("""
                SELECT * FROM events WHERE event_id = #{eventId}
            """)
    Event getEventById(Integer eventId);


    @ResultMap("eventMapper")
    @Select("""
                INSERT INTO events VALUES (default, #{req.eventName}, #{req.eventDate}::date, #{req.venueId}) RETURNING *;
            """)
    Event createEvent(@Param("req") EventRequest request);


    @ResultMap("eventMapper")
    @Select("""
                SELECT EXISTS(
                    SELECT 1 FROM events 
                    WHERE event_name = #{name} 
                    AND event_date = #{date}::date)
            """)
    Boolean existsByEventNameAndEventDate(@Param("name") String eventName, @Param("date") String eventDate);


    @ResultMap("eventMapper")
    @Select("""
        DELETE FROM events WHERE event_id = #{eventId};
    """)
    Event deleteEventById(Integer eventId);
}
