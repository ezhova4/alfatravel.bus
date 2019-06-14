package com.ui.alfabank.alfatravel.bus.entities.providerResponses.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @JsonIgnore
    private final String dateTimePattern = "dd.MM.yyyy HH:mm";

    @JsonProperty(value = "reservation_id")
    private String reservationId;
    private String currency;
    @JsonProperty(value = "total_cost")
    private BigDecimal totalCost;
    private int status;
    @JsonProperty(value = "can_be_returned")
    private boolean canBeReturned;
    @JsonProperty(value = "refund_amount")
    private BigDecimal refundAmount;
    @JsonProperty(value = "bus_number")
    private String busNumber;
    @JsonProperty(value = "carrier")
    private String carrier;
    @JsonProperty(value = "payment_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = dateTimePattern)
    private LocalDateTime paymentDate;
    @JsonProperty(value = "created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = dateTimePattern)
    private LocalDateTime createdAt;
    @JsonProperty(value = "ticket_in_bus")
    private boolean ticketInBus;
    @JsonProperty(value = "travelers_count")
    private int travelersCount;
    @JsonProperty(value = "arrival_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = dateTimePattern)
    private LocalDateTime arrivalDate;
    private String arrival;
    @JsonProperty(value = "arrival_address")
    private String arrivalAddress;
    @JsonProperty(value = "arrival_code")
    private String arrivalCode;
    @JsonProperty(value = "departure_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = dateTimePattern)
    private LocalDateTime departureDate;
    private String departure;
    @JsonProperty(value = "departure_address")
    private String departureAddress;
    @JsonProperty(value = "departure_code")
    private String departureCode;
    private Amount amounts;
    private List<Document> documents;
}
