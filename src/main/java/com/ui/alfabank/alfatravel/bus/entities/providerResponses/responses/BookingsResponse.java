package com.ui.alfabank.alfatravel.bus.entities.providerResponses.responses;

import com.ui.alfabank.alfatravel.bus.entities.providerResponses.json.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingsResponse extends Response{
    private List<Booking> bookings;
}
