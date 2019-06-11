package com.ui.alfabank.alfatravel.bus.service;

import com.ui.alfabank.alfatravel.bus.entities.exceptions.APIResponseException;
import com.ui.alfabank.alfatravel.bus.entities.providerResponses.*;
import com.ui.alfabank.alfatravel.bus.entities.requests.*;
import com.ui.alfabank.alfatravel.bus.repository.TTNBusTicketRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "provider.TTN.enable")
public class TTNBusTicketService implements BusTicketService {

    private TTNBusTicketRepository TTNBusTicketRepository;

    public TTNBusTicketService(TTNBusTicketRepository TTNBusTicketRepository) {
        this.TTNBusTicketRepository = TTNBusTicketRepository;
    }

    @Override
    public BusSearchStations findStations(SearchStationRequest searchStationRequest, String key, String lang) throws APIResponseException {
        return TTNBusTicketRepository.findStations(searchStationRequest, key, lang);
    }

    @Override
    public BusSearchRoutes findRoutes(SearchRoutesRequest searchRoutesRequest, String key, String lang) throws APIResponseException {
        return TTNBusTicketRepository.findRoutes(searchRoutesRequest, key, lang);
    }

    @Override
    public BusPrice getPrice(PriceRequest priceRequest, String key, String lang) throws APIResponseException {
        return TTNBusTicketRepository.getPrice(priceRequest, key, lang);
    }

    @Override
    public BusCreateBooking createBooking(CreateBookingRequest createBookingRequest, String key, String lang) throws APIResponseException {
        return TTNBusTicketRepository.createBooking(createBookingRequest, key, lang);
    }

    @Override
    public BusRoute getRouteInfo(RouteRequest routeRequest, String key, String lang) throws APIResponseException {
        return TTNBusTicketRepository.getRouteInfo(routeRequest, key, lang);
    }

    @Override
    public BusBookings getBookings(GetBookingsRequest getBookingsRequest, String key, String lang) throws APIResponseException {
        return TTNBusTicketRepository.getBookings(getBookingsRequest, key, lang);
    }

    @Override
    public BusBooking getBookingInfo(GetBookingRequest getBookingRequest, String key, String lang) throws APIResponseException {
        return TTNBusTicketRepository.getBookingInfo(getBookingRequest, key, lang);
    }

    @Override
    public BusBooking getBookingPdfVoucher(GetBookingPdfVoucherRequest getBookingPdfVoucherRequest, String key, String lang) throws APIResponseException {
        return TTNBusTicketRepository.getBookingPdfVoucher(getBookingPdfVoucherRequest, key, lang);
    }

    @Override
    public BusBooking cancelBooking(CancelBookingRequest cancelBookingRequest, String key, String lang) throws APIResponseException {
        return TTNBusTicketRepository.cancelBooking(cancelBookingRequest, key, lang);
    }
}
