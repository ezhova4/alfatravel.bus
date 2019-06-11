package com.ui.alfabank.alfatravel.bus.entities.providerResponses.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Station {
    private long id;
    @JsonProperty(value = "country_code")
    private String countryCode;
    @JsonProperty(value = "name_ru")
    private String nameRu;
    @JsonProperty(value = "name_uk")
    private String nameUk;
    @JsonProperty(value = "name_en")
    private String nameEn;
}
