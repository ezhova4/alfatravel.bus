package com.ui.alfabank.alfatravel.bus.entities.providerResponses.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {
    private final String timePattern = "HH:mm";
    private final String datePattern = "yyyy-MM-dd";

    @JsonProperty("departure_name")
    private String departureName;
    @JsonProperty("departure_address")
    private String departureAddress;
    @JsonProperty("departure_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = datePattern)
    private LocalDate departureDate;
    @JsonProperty("departure_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = timePattern)
    private LocalTime departureTime;
    @JsonProperty("departure_timestamp")
    private Integer departureTimestamp;
    @JsonProperty("arrival_name")
    private String arrivalName;
    @JsonProperty("arrival_address")
    private String arrivalAddress;
    @JsonProperty("arrival_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = datePattern)
    private LocalDate arrivalDate;
    @JsonProperty("arrival_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = timePattern)
    private LocalTime arrivalTime;
    @JsonProperty("arrival_timestamp")
    private Long arrivalTimestamp;
    @JsonProperty("travel_time")
    private Long travelTime;
    @JsonProperty("bus_name")
    private String busName;
    @JsonProperty("bus_number")
    private String busNumber;
    @JsonProperty("route_name")
    private String routeName;
    @JsonProperty("id")
    private String id;
    @JsonProperty("free_places")
    private Integer freePlaces;
    @JsonProperty("free_place_numbers")
    private List<String> freePlaceNumbers;
    @JsonProperty("price")
    private Integer price;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("registration_in_bus")
    private Boolean registrationInBus;
    @JsonProperty("carrier")
    private String carrier;
    @JsonProperty("has_place_numbers")
    private Boolean hasPlaceNumbers;
    @JsonProperty("need_birth")
    private Boolean needBirth;
    @JsonProperty("need_doc")
    private Boolean needDoc;
    @JsonProperty("need_nationality")
    private Boolean needNationality;
    @JsonProperty("need_patronymic")
    private Boolean needPatronymic;
    @JsonProperty("need_gender")
    private Boolean needGender;
    @JsonProperty("need_latin_names")
    private Boolean needLatinNames;
    @JsonProperty("amounts")
    private Amount amounts;
    @JsonProperty("discounts")
    private List<Item> discounts;
    @JsonProperty("documents")
    private List<Item> documents;

//    public void setTravelTime(Long travelTime) {
//        this.travelTime = Instant.ofEpochSecond(travelTime)
//                .atOffset(ZoneOffset.ofHours(0))
//                .toLocalTime();
//    }
//    public LocalTime getTravelTime() {
//        return travelTime;
//    }
}
