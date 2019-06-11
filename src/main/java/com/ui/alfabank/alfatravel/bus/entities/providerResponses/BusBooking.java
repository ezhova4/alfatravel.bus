package com.ui.alfabank.alfatravel.bus.entities.providerResponses;

import com.ui.alfabank.alfatravel.bus.entities.providerResponses.responses.BookingResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusBooking extends Bus {
    private BookingResponse response;
}
