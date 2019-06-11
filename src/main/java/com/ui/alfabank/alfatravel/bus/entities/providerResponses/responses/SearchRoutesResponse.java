package com.ui.alfabank.alfatravel.bus.entities.providerResponses.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ui.alfabank.alfatravel.bus.entities.providerResponses.json.Route;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRoutesResponse extends Response {

    @JsonProperty(value = "session_id")
    private String sessionId;
    private List<Route> buses;
}
