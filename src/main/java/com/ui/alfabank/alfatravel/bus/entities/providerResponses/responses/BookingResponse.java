package com.ui.alfabank.alfatravel.bus.entities.providerResponses.responses;

import com.ui.alfabank.alfatravel.bus.entities.providerResponses.json.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse extends Response {
    private Booking booking;
}
