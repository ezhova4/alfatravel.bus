package com.ui.alfabank.alfatravel.bus.entities.providerResponses.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerTicketPrice {
    @JsonProperty(value = "ticket_price")
    private BigDecimal ticketPrice;
    private String currency;
    private String hash;
    private Amount amounts;
}
