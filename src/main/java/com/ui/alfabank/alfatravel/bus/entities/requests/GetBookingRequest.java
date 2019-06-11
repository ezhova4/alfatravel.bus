package com.ui.alfabank.alfatravel.bus.entities.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBookingRequest extends BookingRequest implements Request {
    @JsonProperty(value = "reservation_id")
    private String reservationId;
}
