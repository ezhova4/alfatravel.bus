package com.ui.alfabank.alfatravel.bus.repository;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(value = "tags")
public class Tags {
    private String sessionId;
    private String extendedToken;
    private String type;
    private String limit;
    private String query;
    private String from;
    private String to;
    private String date;
    private String id;
    private String token;
    private String key;
    private String lang;
    private String birthday;
    private String citizenship;
    private String discountId;
    private String documentNumber;
    private String documentType;
    private String gender;
    private String middleName;
    private String name;
    private String placeNumber;
    private String surname;
    private String passenger;
}
