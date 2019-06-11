package com.ui.alfabank.alfatravel.bus.entities.providerResponses.responses;

import com.ui.alfabank.alfatravel.bus.entities.providerResponses.json.Station;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchStationsResponse extends Response {
    List<Station> stations;
}
