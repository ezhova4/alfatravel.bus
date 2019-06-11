package com.ui.alfabank.alfatravel.bus.service;

import com.ui.alfabank.alfatravel.bus.entities.exceptions.APIResponseException;
import com.ui.alfabank.alfatravel.bus.entities.providerResponses.*;
import com.ui.alfabank.alfatravel.bus.entities.requests.*;

public interface BusTicketService {
    BusSearchStations findStations(SearchStationRequest searchStationRequest, String key, String lang) throws APIResponseException;

    BusSearchRoutes findRoutes(SearchRoutesRequest searchRoutesRequest, String key, String lang) throws APIResponseException;

    BusPrice getPrice(PriceRequest priceRequest, String key, String lang) throws APIResponseException;

    BusCreateBooking createBooking(CreateBookingRequest createBookingRequest, String key, String lang) throws APIResponseException;

    BusRoute getRouteInfo(RouteRequest routeRequest, String key, String lang) throws APIResponseException;

    BusBookings getBookings(GetBookingsRequest getBookingsRequest, String key, String lang) throws APIResponseException;

    BusBooking getBookingInfo(GetBookingRequest getBookingRequest, String key, String lang) throws APIResponseException;

    BusBooking getBookingPdfVoucher(GetBookingPdfVoucherRequest getBookingPdfVoucherRequest, String key, String lang) throws APIResponseException;

    BusBooking cancelBooking(CancelBookingRequest cancelBookingRequest, String key, String lang) throws APIResponseException;
}
