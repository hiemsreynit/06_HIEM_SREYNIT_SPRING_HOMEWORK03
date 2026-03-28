package com.hw.exception.hiem_sreynit_spring_homework03.repository;

import com.hw.exception.hiem_sreynit_spring_homework03.model.entity.Venue;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VenueRepository {

    @Results(id = "venueMapper", value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name")
    })

    @Select("""
        SELECT * FROM venues OFFSET #{offset} LIMIT #{size};
    """)
    List<Venue> getAllVenues(int offset, Integer size);

    @ResultMap("venueMapper")
    @Select("""
        SELECT * FROM venues WHERE venue_id = #{venueId};
    """)
    Venue getVenueById(Integer venueId);

    @ResultMap("venueMapper")
    @Select("""
        DELETE FROM venues WHERE venue_id = #{venueId};
    """)
    void deleteVenueById(Integer venueId);
}
