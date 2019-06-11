package com.ui.alfabank.alfatravel.bus.entities.providerResponses.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ui.alfabank.alfatravel.bus.entities.providerResponses.json.PassengerTicketPrice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceResponse extends Response {
    @JsonProperty(value = "session_id")
    private String sessionId;
    private List<PassengerTicketPrice> passengers;
}
