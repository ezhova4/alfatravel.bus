package com.ui.alfabank.alfatravel.bus.entities.providerResponses;

import com.ui.alfabank.alfatravel.bus.entities.providerResponses.responses.SearchStationsResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusSearchStations extends Bus {
    private SearchStationsResponse response;
}
