package com.ui.alfabank.alfatravel.bus.entities.requests.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {
    private String birthday;
    private String citizenship;
    @JsonProperty(value = "discount_id")
    private int discountId;
    @JsonProperty(value = "document_number")
    private String documentNumber;
    @JsonProperty(value = "document_type")
    private String documentType;
    private String gender;
    private String midlename;
    private String name;
    @JsonProperty(value = "place_number")
    private int placeNumber;
    private String surname;
}
