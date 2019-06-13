package com.ui.alfabank.alfatravel.bus.entities.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchStationRequest implements Request{
    @NotNull
    private int type;
    @NotNull
    private int limit;
    @NotBlank
    private String query;
}
