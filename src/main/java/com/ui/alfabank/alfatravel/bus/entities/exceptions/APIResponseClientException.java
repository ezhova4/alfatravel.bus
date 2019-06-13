package com.ui.alfabank.alfatravel.bus.entities.exceptions;

import lombok.Data;

@Data
public class APIResponseClientException extends Exception {
    private String description;

    public APIResponseClientException(String message, String description) {
        super(message);
        this.description = description;
    }
}
