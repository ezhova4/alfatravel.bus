package com.ui.alfabank.alfatravel.bus.entities.providerResponses;

import com.ui.alfabank.alfatravel.bus.entities.providerResponses.responses.PriceResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusPrice extends Bus {
    private PriceResponse response;
}
