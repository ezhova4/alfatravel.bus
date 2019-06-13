package com.ui.alfabank.alfatravel.bus.entities.providerResponses;

public enum  ResponseStatusCode {
    BAD_RESPONSE,
    BAD_REQUEST,
    /**
     *  Неправильный запрос от клиента(фронт)
     */
    BAD_REQUEST_CLIENT,
    /**
     *  Проблемы на стороне API
     */
    BAD_API_RESPONSE,
    /**
     *  Запрос от клиента(фронт) не валидный
     */
    BAD_REQUEST_VALIDATION
}
