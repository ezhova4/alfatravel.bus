package com.ui.alfabank.alfatravel.bus.entities.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class APIResponseException extends Exception {
    private HttpStatus httpStatus;

    public APIResponseException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

}
