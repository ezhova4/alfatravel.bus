package com.ui.alfabank.alfatravel.bus.entities.providerResponses;

import com.ui.alfabank.alfatravel.bus.entities.providerResponses.responses.CreateBookingResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusCreateBooking extends Bus {
    private CreateBookingResponse response;
}
