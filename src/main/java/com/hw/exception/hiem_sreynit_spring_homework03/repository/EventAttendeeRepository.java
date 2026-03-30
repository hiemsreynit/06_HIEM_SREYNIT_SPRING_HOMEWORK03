package com.hw.exception.hiem_sreynit_spring_homework03.repository;

import com.hw.exception.hiem_sreynit_spring_homework03.model.entity.Attendee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventAttendeeRepository {

    @Results(id = "eventAttendeeMapper", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name")
    })

    @Select("""
        SELECT * FROM attendees a
        INNER JOIN event_attendee ea ON a.attendee_id = ea.attendee_id
        WHERE ea.event_id = #{eventId};
    """)
    List<Attendee> getAttendeeByEventId(@Param("eventId") Integer eventId);


    @ResultMap("eventAttendeeMapper")
    @Insert("""
        INSERT INTO event_attendee VALUES (#{eventId}, #{attendeeId}) RETURNING *;
    """)
    void insertIntoEventAndAttendee(Integer eventId, Integer attendeeId);


    @ResultMap("eventAttendeeMapper")
    @Delete("""
        DELETE FROM event_attendee WHERE event_id = #{eventId};
    """)
    void deleteAttendeeByEventId(Integer eventId);

}
