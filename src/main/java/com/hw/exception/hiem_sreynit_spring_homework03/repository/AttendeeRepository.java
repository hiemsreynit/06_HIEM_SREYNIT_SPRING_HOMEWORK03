package com.hw.exception.hiem_sreynit_spring_homework03.repository;

import com.hw.exception.hiem_sreynit_spring_homework03.model.dto.request.AttendeeRequest;
import com.hw.exception.hiem_sreynit_spring_homework03.model.entity.Attendee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttendeeRepository {


    @Results(id = "attendeeMapper", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name")
    })

    @Select("""
        SELECT * FROM attendees OFFSET #{offset} LIMIT #{size};
    """)
    List<Attendee> getAllAttendees(int offset, Integer size);


    @ResultMap("attendeeMapper")
    @Select("""
        SELECT * FROM attendees WHERE attendee_id = #{attendeeId};
    """)
    Attendee getAttendeeById(Integer attendeeId);


    @ResultMap("attendeeMapper")
    @Select("""
        INSERT INTO attendees VALUES (default, #{req.attendeeName}, #{req.email}) RETURNING *;
    """)
    Attendee createAttendee(@Param("req") AttendeeRequest request);


    @ResultMap("attendeeMapper")
    @Select("""
        DELETE FROM attendees WHERE attendee_id = #{attendeeId};
    """)
    Attendee deleteAttendeeById(Integer attendeeId);


    @ResultMap("attendeeMapper")
    @Select("""
        UPDATE attendees SET attendee_name = #{req.attendeeName}, email = #{req.email} WHERE attendee_id = #{attendeeId} RETURNING *;
    """)
    void updateAttendeeById(Integer attendeeId, @Param("req") AttendeeRequest request);
}
