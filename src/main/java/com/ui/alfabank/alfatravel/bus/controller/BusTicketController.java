package com.ui.alfabank.alfatravel.bus.controller;

import com.ui.alfabank.alfatravel.bus.entities.error.ErrorResponse;
import com.ui.alfabank.alfatravel.bus.entities.exceptions.APIResponseException;
import com.ui.alfabank.alfatravel.bus.entities.providerResponses.*;
import com.ui.alfabank.alfatravel.bus.entities.requests.*;
import com.ui.alfabank.alfatravel.bus.service.BusTicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@CrossOrigin
@RestController
public class BusTicketController {

    @Value("${msg.controller.logPost}")
    private String logPost;

    private BusTicketService busTicketService;

    public BusTicketController(BusTicketService busTicketService) {
        this.busTicketService = busTicketService;
    }

    @PostMapping("/stations")
    public ResponseEntity searchStations(@RequestBody @Valid SearchStationRequest searchStationRequest,
                                         @RequestHeader("${provider.TTN.header.key}") String key,
                                         @RequestHeader("${provider.TTN.header.lang}") String lang) throws APIResponseException {
        log.info(logPost, searchStationRequest);
        BusSearchStations busSearchStations = busTicketService.findStations(searchStationRequest, key, lang);
        log.info(logPost, busSearchStations);
        return ResponseEntity.ok(busSearchStations);
    }

    @PostMapping("/routes")
    public ResponseEntity searchRoutes(@RequestBody @Valid SearchRoutesRequest searchRoutesRequest,
                                       @RequestHeader("${provider.TTN.header.key}") String key,
                                       @RequestHeader("${provider.TTN.header.lang}") String lang) throws APIResponseException {
        log.info(logPost, searchRoutesRequest);
        BusSearchRoutes busSearchRoutes = busTicketService.findRoutes(searchRoutesRequest, key, lang);
        log.info(logPost, busSearchRoutes);
        return ResponseEntity.ok(busSearchRoutes);
    }

    @PostMapping("/price")
    public ResponseEntity getPrice(@RequestBody @Valid PriceRequest priceRequest,
                                   @RequestHeader("${provider.TTN.header.key}") String key,
                                   @RequestHeader("${provider.TTN.header.lang}") String lang) throws APIResponseException {
        log.info(logPost, priceRequest);
        BusPrice price = busTicketService.getPrice(priceRequest, key, lang);
        log.info(logPost, price);
        return ResponseEntity.ok(price);
    }

    @PostMapping("/createBooking")
    public ResponseEntity createBooking(@RequestBody @Valid CreateBookingRequest createBookingRequest,
                                        @RequestHeader("${provider.TTN.header.key}") String key,
                                        @RequestHeader("${provider.TTN.header.lang}") String lang) throws APIResponseException {
        log.info(logPost, createBookingRequest);
        BusCreateBooking booking = busTicketService.createBooking(createBookingRequest, key, lang);
        log.info(logPost, booking);
        return ResponseEntity.ok(booking);
    }

    @PostMapping("/route")
    public ResponseEntity getRouteInfo(@RequestBody @Valid RouteRequest routeRequest,
                                       @RequestHeader("${provider.TTN.header.key}") String key,
                                       @RequestHeader("${provider.TTN.header.lang}") String lang) throws APIResponseException {
        log.info(logPost, routeRequest);
        BusRoute route = busTicketService.getRouteInfo(routeRequest, key, lang);
        log.info(logPost, route);
        return ResponseEntity.ok(route);
    }

    @PostMapping("/bookings")
    public ResponseEntity getBookings(@RequestBody @Valid GetBookingsRequest getBookingsRequest,
                                      @RequestHeader("${provider.TTN.header.key}") String key,
                                      @RequestHeader("${provider.TTN.header.lang}") String lang) throws APIResponseException {
        log.info(logPost, getBookingsRequest);
        BusBookings booking = busTicketService.getBookings(getBookingsRequest, key, lang);
        log.info(logPost, booking);
        return ResponseEntity.ok(booking);
    }

    @PostMapping("/booking")
    public ResponseEntity getBookingInfo(@RequestBody @Valid GetBookingRequest getBookingRequest,
                                         @RequestHeader("${provider.TTN.header.key}") String key,
                                         @RequestHeader("${provider.TTN.header.lang}") String lang) throws APIResponseException {
        log.info(logPost, getBookingRequest);
        BusBooking booking = busTicketService.getBookingInfo(getBookingRequest, key, lang);
        log.info(logPost, booking);
        return ResponseEntity.ok(booking);
    }

    @PostMapping("/bookingPdfVoucher")
    public ResponseEntity getBookingPdfVoucher(@RequestBody @Valid GetBookingPdfVoucherRequest getBookingPdfVoucherRequest,
                                               @RequestHeader("${provider.TTN.header.key}") String key,
                                               @RequestHeader("${provider.TTN.header.lang}") String lang) throws APIResponseException {
        log.info(logPost, getBookingPdfVoucherRequest);
        BusBooking booking = busTicketService.getBookingPdfVoucher(getBookingPdfVoucherRequest, key, lang);
        log.info(logPost, booking);
        return ResponseEntity.ok(booking);
    }

    @PostMapping("/cancelBooking")
    public ResponseEntity cancelBooking(@RequestBody @Valid CancelBookingRequest cancelBookingRequest,
                                        @RequestHeader("${provider.TTN.header.key}") String key,
                                        @RequestHeader("${provider.TTN.header.lang}") String lang) throws APIResponseException {
        log.info(logPost, cancelBookingRequest);
        BusBooking booking = busTicketService.cancelBooking(cancelBookingRequest, key, lang);
        log.info(logPost, booking);
        return ResponseEntity.ok(booking);
    }

    @ExceptionHandler(APIResponseException.class)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity APIResponseException(APIResponseException e) {
        log.warn(ResponseStatusCode.BAD_RESPONSE + ": { " + e.getMessage() + " }");

        return ResponseEntity.ok(ErrorResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.toString())
                .error(ResponseStatusCode.BAD_RESPONSE.toString())
                .message(e.getMessage())
                .build());
    }
}
