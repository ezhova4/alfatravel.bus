package com.ui.alfabank.alfatravel.bus.entities.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBookingRequest extends BookingRequest implements Request {
    @NotBlank
    @JsonProperty(value = "reservation_id")
    private String reservationId;
}
