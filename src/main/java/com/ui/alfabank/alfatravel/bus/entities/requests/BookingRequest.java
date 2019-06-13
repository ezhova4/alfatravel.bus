package com.ui.alfabank.alfatravel.bus.entities.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
class BookingRequest {
    @JsonProperty(value = "extended_token")
    private String extendedToken;
    @NotBlank
    private String token;
}
