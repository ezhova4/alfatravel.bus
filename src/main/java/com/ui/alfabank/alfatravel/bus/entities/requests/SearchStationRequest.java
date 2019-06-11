package com.ui.alfabank.alfatravel.bus.entities.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchStationRequest implements  Request{
    private int type;
    private int limit;
    private String query;
}
