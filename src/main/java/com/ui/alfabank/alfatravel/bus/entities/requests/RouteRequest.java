package com.ui.alfabank.alfatravel.bus.entities.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteRequest implements Request {
    private String id;
    @JsonProperty(value = "extended_token")
    private String extendedToken;
    @JsonProperty(value = "session_id")
    private String sessionId;
}
