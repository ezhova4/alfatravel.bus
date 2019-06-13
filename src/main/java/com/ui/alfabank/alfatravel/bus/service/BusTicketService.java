package com.ui.alfabank.alfatravel.bus.service;

import com.ui.alfabank.alfatravel.bus.entities.exceptions.APIResponseClientException;
import com.ui.alfabank.alfatravel.bus.entities.exceptions.APIResponseException;
import com.ui.alfabank.alfatravel.bus.entities.exceptions.NoSuchResultException;
import com.ui.alfabank.alfatravel.bus.entities.providerResponses.*;
import com.ui.alfabank.alfatravel.bus.entities.requests.*;

public interface BusTicketService {
    BusSearchStations findStations(SearchStationRequest searchStationRequest, String key, String lang) throws APIResponseException, NoSuchResultException, APIResponseClientException;

    BusSearchRoutes findRoutes(SearchRoutesRequest searchRoutesRequest, String key, String lang) throws APIResponseException, NoSuchResultException, APIResponseClientException;

    BusPrice getPrice(PriceRequest priceRequest, String key, String lang) throws APIResponseException, NoSuchResultException, APIResponseClientException;

    BusCreateBooking createBooking(CreateBookingRequest createBookingRequest, String key, String lang) throws APIResponseException, NoSuchResultException, APIResponseClientException;

    BusRoute getRouteInfo(RouteRequest routeRequest, String key, String lang) throws APIResponseException, NoSuchResultException, APIResponseClientException;

    BusBookings getBookings(GetBookingsRequest getBookingsRequest, String key, String lang) throws APIResponseException, NoSuchResultException, APIResponseClientException;

    BusBooking getBookingInfo(GetBookingRequest getBookingRequest, String key, String lang) throws APIResponseException, NoSuchResultException, APIResponseClientException;

    BusBooking getBookingPdfVoucher(GetBookingPdfVoucherRequest getBookingPdfVoucherRequest, String key, String lang) throws APIResponseException, NoSuchResultException, APIResponseClientException;

    BusBooking cancelBooking(CancelBookingRequest cancelBookingRequest, String key, String lang) throws APIResponseException, NoSuchResultException, APIResponseClientException;
}
