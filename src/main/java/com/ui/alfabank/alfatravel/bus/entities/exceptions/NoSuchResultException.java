package com.ui.alfabank.alfatravel.bus.entities.exceptions;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class NoSuchResultException extends Exception{
    @Value("${msg.exceptions.noSuchResult}")
    private String defaultMessage;

    public NoSuchResultException() {
    }

}
