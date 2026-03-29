package com.hw.exception.hiem_sreynit_spring_homework03.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    private String eventName;
    private String eventDate;
    private Integer venueId;
    private List<Integer> attendeesIds;
}
