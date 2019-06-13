package com.ui.alfabank.alfatravel.bus.entities.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ui.alfabank.alfatravel.bus.entities.requests.json.Passenger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceRequest implements Request {
    @NotBlank
    private String id;
    @JsonProperty(value = "extended_token")
    private String extendedToken;
    @NotBlank
    @JsonProperty(value = "session_id")
    private String sessionId;
    @NotNull
    private List<Passenger> passengers;
}
