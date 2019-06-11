package com.ui.alfabank.alfatravel.bus.entities.providerResponses.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Document {
    @JsonProperty(value = "passenger_info")
    private String passengerInfo;
    @JsonProperty(value = "place_number")
    private String placeNumber;
    private String uid;
    @JsonProperty(value = "discount_description")
    private String discountDescription;
    @JsonProperty(value = "ticket_number")
    private String ticketNumber;
}
