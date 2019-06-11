package com.ui.alfabank.alfatravel.bus.entities.providerResponses;

import com.ui.alfabank.alfatravel.bus.entities.providerResponses.responses.RouteResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusRoute extends Bus {
    private RouteResponse response;
}
