package com.ui.alfabank.alfatravel.bus.entities.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteRequest implements Request {
    @NotBlank
    private String id;
    @JsonProperty(value = "extended_token")
    private String extendedToken;
    @NotBlank
    @JsonProperty(value = "session_id")
    private String sessionId;
}
