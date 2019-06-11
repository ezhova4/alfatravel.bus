package com.ui.alfabank.alfatravel.bus.entities.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorResponse {
    private String statusCode;
    private String error;
    private String message;
}
