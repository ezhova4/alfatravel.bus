package com.ui.alfabank.alfatravel.bus.entities.providerResponses.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private String code;

    private String description;

    @JsonProperty(value = "execution_time")
    private String executionTime;
}
