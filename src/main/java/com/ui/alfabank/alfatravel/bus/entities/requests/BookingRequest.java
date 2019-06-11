package com.ui.alfabank.alfatravel.bus.entities.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ui.alfabank.alfatravel.bus.repository.Tags;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
public class BookingRequest {
    @JsonProperty(value = "extended_token")
    private String extendedToken;
    private String token;
}
