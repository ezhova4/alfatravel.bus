package com.ui.alfabank.alfatravel.bus.entities.providerResponses.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ui.alfabank.alfatravel.bus.entities.providerResponses.json.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookingResponse extends Response {
    @JsonProperty(value = "session_id")
    private String sessionId;
    private Booking booking;
}
