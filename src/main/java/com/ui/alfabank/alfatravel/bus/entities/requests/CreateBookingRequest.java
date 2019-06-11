package com.ui.alfabank.alfatravel.bus.entities.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ui.alfabank.alfatravel.bus.entities.requests.json.Passenger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookingRequest extends BookingRequest implements Request {
    private int id;
    @JsonProperty(value = "session_id")
    private String sessionId;
    private List<Passenger> passengers;
}
