package com.hw.exception.hiem_sreynit_spring_homework03.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VenueRequest {

    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 150, message = "Venue name must be between 3 and 150 characters.")
    private String venueName;

    @NotBlank(message = "Location is required.")
    @Size(min = 5, max = 500, message = "Location is too short or too long.")
    private String location;

}
