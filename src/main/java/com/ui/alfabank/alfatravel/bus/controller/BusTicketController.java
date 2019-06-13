package com.ui.alfabank.alfatravel.bus.controller;

import com.ui.alfabank.alfatravel.bus.entities.error.ErrorResponse;
import com.ui.alfabank.alfatravel.bus.entities.exceptions.APIResponseClientException;
import com.ui.alfabank.alfatravel.bus.entities.exceptions.APIResponseException;
import com.ui.alfabank.alfatravel.bus.entities.exceptions.NoSuchResultException;
import com.ui.alfabank.alfatravel.bus.entities.providerResponses.*;
import com.ui.alfabank.alfatravel.bus.entities.requests.*;
import com.ui.alfabank.alfatravel.bus.service.BusTicketService;
import com.ui.alfabank.alfatravel.bus.util.Bracers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/bus")
public class BusTicketController {

    @Value("${msg.controller.logPostRequest}")
    private String logPostRequest;
    @Value("${msg.controller.logPostResponse}")
    private String logPostResponse;

    @Value("${msg.controller.logAPIResponseClientException}")
    private String logAPIResponseClientException;
    @Value("${msg.controller.logMethodArgumentNotValidException}")
    private String logMethodArgumentNotValidException;
    @Value("${msg.controller.logAPIResponseException}")
    private String logAPIResponseException;
    @Value("${msg.controller.logHttpMessageNotReadableException}")
    private String logHttpMessageNotReadableException;


    private BusTicketService busTicketService;

    public BusTicketController(BusTicketService busTicketService) {
        this.busTicketService = busTicketService;
    }

    @PostMapping(value = "/stations",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity searchStations(@RequestBody @Valid SearchStationRequest searchStationRequest,
                                         @RequestHeader("${provider.TTN.header.key}") @NotBlank String key,
                                         @RequestHeader("${provider.TTN.header.lang}") @NotBlank String lang) throws APIResponseException, NoSuchResultException, APIResponseClientException {
        log.info(logPostRequest + Bracers.createBracers(3), searchStationRequest, key, lang);
        BusSearchStations busSearchStations = busTicketService.findStations(searchStationRequest, key, lang);
        log.info(logPostResponse + Bracers.createBracers(1), busSearchStations);
        return ResponseEntity.ok(busSearchStations);
    }

    @PostMapping(value = "/routes",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity searchRoutes(@RequestBody @Valid SearchRoutesRequest searchRoutesRequest,
                                       @RequestHeader("${provider.TTN.header.key}") @NotBlank String key,
                                       @RequestHeader("${provider.TTN.header.lang}") @NotBlank String lang) throws APIResponseException, NoSuchResultException, APIResponseClientException {
        log.info(logPostRequest + Bracers.createBracers(3), searchRoutesRequest, key, lang);
        BusSearchRoutes busSearchRoutes = busTicketService.findRoutes(searchRoutesRequest, key, lang);
        log.info(logPostResponse + Bracers.createBracers(1), busSearchRoutes);
        return ResponseEntity.ok(busSearchRoutes);
    }

    @PostMapping(value = "/price",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getPrice(@RequestBody @Valid PriceRequest priceRequest,
                                   @RequestHeader("${provider.TTN.header.key}") @NotBlank String key,
                                   @RequestHeader("${provider.TTN.header.lang}") @NotBlank String lang) throws APIResponseException, NoSuchResultException, APIResponseClientException {
        log.info(logPostRequest + Bracers.createBracers(3), priceRequest, key, lang);
        BusPrice price = busTicketService.getPrice(priceRequest, key, lang);
        log.info(logPostResponse + Bracers.createBracers(1), price);
        return ResponseEntity.ok(price);
    }

    @PostMapping(value = "/createBooking",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createBooking(@RequestBody @Valid CreateBookingRequest createBookingRequest,
                                        @RequestHeader("${provider.TTN.header.key}") @NotBlank String key,
                                        @RequestHeader("${provider.TTN.header.lang}") @NotBlank String lang) throws APIResponseException, NoSuchResultException, APIResponseClientException {
        log.info(logPostRequest + Bracers.createBracers(3), createBookingRequest, key, lang);
        BusCreateBooking booking = busTicketService.createBooking(createBookingRequest, key, lang);
        log.info(logPostResponse + Bracers.createBracers(1), booking);
        return ResponseEntity.ok(booking);
    }

    @PostMapping(value = "/routeInfo",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getRouteInfo(@RequestBody @Valid RouteRequest routeRequest,
                                       @RequestHeader("${provider.TTN.header.key}") @NotBlank String key,
                                       @RequestHeader("${provider.TTN.header.lang}") @NotBlank String lang) throws APIResponseException, NoSuchResultException, APIResponseClientException {
        log.info(logPostRequest + Bracers.createBracers(3), routeRequest, key, lang);
        BusRoute route = busTicketService.getRouteInfo(routeRequest, key, lang);
        log.info(logPostResponse + Bracers.createBracers(1), route);
        return ResponseEntity.ok(route);
    }

    @PostMapping(value = "/bookings",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getBookings(@RequestBody @Valid GetBookingsRequest getBookingsRequest,
                                      @RequestHeader("${provider.TTN.header.key}") @NotBlank String key,
                                      @RequestHeader("${provider.TTN.header.lang}") @NotBlank String lang) throws APIResponseException, NoSuchResultException, APIResponseClientException {
        log.info(logPostRequest + Bracers.createBracers(3), getBookingsRequest, key, lang);
        BusBookings booking = busTicketService.getBookings(getBookingsRequest, key, lang);
        log.info(logPostResponse + Bracers.createBracers(1), booking);
        return ResponseEntity.ok(booking);
    }

    @PostMapping(value = "/bookingInfo",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getBookingInfo(@RequestBody @Valid GetBookingRequest getBookingRequest,
                                         @RequestHeader("${provider.TTN.header.key}") @NotBlank String key,
                                         @RequestHeader("${provider.TTN.header.lang}") @NotBlank String lang) throws APIResponseException, NoSuchResultException, APIResponseClientException {
        log.info(logPostRequest + Bracers.createBracers(3), getBookingRequest, key, lang);
        BusBooking booking = busTicketService.getBookingInfo(getBookingRequest, key, lang);
        log.info(logPostResponse + Bracers.createBracers(1), booking);
        return ResponseEntity.ok(booking);
    }

    @PostMapping(value = "/bookingPdfVoucher",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getBookingPdfVoucher(@RequestBody @Valid GetBookingPdfVoucherRequest getBookingPdfVoucherRequest,
                                               @RequestHeader("${provider.TTN.header.key}") @NotBlank String key,
                                               @RequestHeader("${provider.TTN.header.lang}") @NotBlank String lang) throws APIResponseException, NoSuchResultException, APIResponseClientException {
        log.info(logPostRequest + Bracers.createBracers(3), getBookingPdfVoucherRequest, key, lang);
        BusBooking booking = busTicketService.getBookingPdfVoucher(getBookingPdfVoucherRequest, key, lang);
        log.info(logPostResponse + Bracers.createBracers(1), booking);
        return ResponseEntity.ok(booking);
    }

    @PostMapping(value = "/cancelBooking",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity cancelBooking(@RequestBody @Valid CancelBookingRequest cancelBookingRequest,
                                        @RequestHeader("${provider.TTN.header.key}") @NotBlank String key,
                                        @RequestHeader("${provider.TTN.header.lang}") @NotBlank String lang) throws APIResponseException, NoSuchResultException, APIResponseClientException {
        log.info(logPostRequest + Bracers.createBracers(3), cancelBookingRequest, key, lang);
        BusBooking booking = busTicketService.cancelBooking(cancelBookingRequest, key, lang);
        log.info(logPostResponse + Bracers.createBracers(1), booking);
        return ResponseEntity.ok(booking);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn(logMethodArgumentNotValidException + Bracers.createBracers(2), ResponseStatusCode.BAD_RESPONSE, e.getMessage());
        return ErrorResponse.builder()
                .error(ResponseStatusCode.BAD_REQUEST_VALIDATION.toString())
                .statusCode(HttpStatus.BAD_REQUEST.toString())
                .message("Invalidate parameter - " + Objects.requireNonNull(e.getBindingResult().getFieldError()).getField())
                .build();
    }

    @ExceptionHandler(APIResponseClientException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity handlerAPIResponseClientException(APIResponseClientException e) {
        log.warn(logAPIResponseClientException + Bracers.createBracers(3), ResponseStatusCode.BAD_RESPONSE, e.getMessage(), e.getDescription());
        return ResponseEntity.ok(ErrorResponse.builder()
                .error(ResponseStatusCode.BAD_API_RESPONSE.toString())
                .statusCode(HttpStatus.BAD_REQUEST.toString())
                .message(e.getDescription())
                .build());
    }

    @ExceptionHandler(APIResponseException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity handlerAPIResponseException(APIResponseException e) {
        log.warn(logAPIResponseException + Bracers.createBracers(1), ResponseStatusCode.BAD_REQUEST);
        return ResponseEntity.ok(ErrorResponse.builder()
                .statusCode(e.getHttpStatus().toString())
                .error(ResponseStatusCode.BAD_REQUEST.toString())
                .message(e.getHttpStatus().getReasonPhrase())
                .build());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity handlerHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.warn(logHttpMessageNotReadableException + Bracers.createBracers(2), ResponseStatusCode.BAD_RESPONSE, e.getMessage());
        return ResponseEntity.ok(ErrorResponse.builder()
                .error(ResponseStatusCode.BAD_REQUEST.toString())
                .statusCode(HttpStatus.BAD_REQUEST.toString())
                .message(e.getMessage())
                .build());
    }
}
