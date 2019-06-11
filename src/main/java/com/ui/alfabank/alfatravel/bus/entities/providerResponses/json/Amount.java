package com.ui.alfabank.alfatravel.bus.entities.providerResponses.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Amount {
    @JsonProperty("CHF")
    private BigDecimal CHF;

    @JsonProperty("MXN")
    private BigDecimal MXN;

    @JsonProperty("QAR")
    private BigDecimal QAR;

    @JsonProperty("KZT")
    private BigDecimal KZT;

    @JsonProperty("SAR")
    private BigDecimal SAR;

    @JsonProperty("INR")
    private BigDecimal INR;

    @JsonProperty("UZS")
    private BigDecimal UZS;

    @JsonProperty("AUD")
    private BigDecimal AUD;

    @JsonProperty("ILS")
    private BigDecimal ILS;

    @JsonProperty("PLN")
    private BigDecimal PLN;

    @JsonProperty("MDL")
    private BigDecimal MDL;

    @JsonProperty("GBP")
    private BigDecimal GBP;

    @JsonProperty("BYN")
    private BigDecimal BYN;

    @JsonProperty("AMD")
    private BigDecimal AMD;

    @JsonProperty("KWD")
    private BigDecimal KWD;

    @JsonProperty("PHP")
    private BigDecimal PHP;

    @JsonProperty("TRY")
    private BigDecimal TRY;

    @JsonProperty("BYR")
    private BigDecimal BYR;

    @JsonProperty("RUR")
    private BigDecimal RUR;

    @JsonProperty("AED")
    private BigDecimal AED;

    @JsonProperty("EUR")
    private BigDecimal EUR;

    @JsonProperty("DKK")
    private BigDecimal DKK;

    @JsonProperty("USD")
    private BigDecimal USD;

    @JsonProperty("CAD")
    private BigDecimal CAD;

    @JsonProperty("NOK")
    private BigDecimal NOK;

    @JsonProperty("GEL")
    private BigDecimal GEL;

    @JsonProperty("EGP")
    private BigDecimal EGP;

    @JsonProperty("RON")
    private BigDecimal RON;

    @JsonProperty("AZN")
    private BigDecimal AZN;

    @JsonProperty("CZK")
    private BigDecimal CZK;

    @JsonProperty("OMR")
    private BigDecimal OMR;

    @JsonProperty("KGS")
    private BigDecimal KGS;

    @JsonProperty("SEK")
    private BigDecimal SEK;

    @JsonProperty("UAH")
    private BigDecimal UAH;

    @JsonProperty("BHD")
    private BigDecimal BHD;
}
