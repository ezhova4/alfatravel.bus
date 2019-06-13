package com.ui.alfabank.alfatravel.bus.service;

import com.ui.alfabank.alfatravel.bus.entities.exceptions.APIResponseClientException;
import com.ui.alfabank.alfatravel.bus.entities.exceptions.APIResponseException;
import com.ui.alfabank.alfatravel.bus.entities.exceptions.NoSuchResultException;
import com.ui.alfabank.alfatravel.bus.entities.providerResponses.*;
import com.ui.alfabank.alfatravel.bus.entities.providerResponses.json.Result;
import com.ui.alfabank.alfatravel.bus.entities.requests.*;
import com.ui.alfabank.alfatravel.bus.repository.TTNBusTicketRepository;
import com.ui.alfabank.alfatravel.bus.util.Bracers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@ConditionalOnProperty(name = "provider.TTN.enable")
public class TTNBusTicketService implements BusTicketService {

    @Value("${msg.service.logService}")
    private String logService;
    @Value("${msg.service.checkResultForErrors}")
    private String checkResultForErrors;
    @Value("${msg.service.clientError}")
    private String clientError;

    private TTNBusTicketRepository TTNBusTicketRepository;

    public TTNBusTicketService(TTNBusTicketRepository TTNBusTicketRepository) {
        this.TTNBusTicketRepository = TTNBusTicketRepository;
    }

    @Override
    public BusSearchStations findStations(SearchStationRequest searchStationRequest, String key, String lang) throws APIResponseException, NoSuchResultException, APIResponseClientException {
        BusSearchStations stations = TTNBusTicketRepository.findStations(searchStationRequest, key, lang);
        checkResultForErrors(stations);
        return stations;
    }

    @Override
    public BusSearchRoutes findRoutes(SearchRoutesRequest searchRoutesRequest, String key, String lang) throws APIResponseException, NoSuchResultException, APIResponseClientException {
        BusSearchRoutes routes = TTNBusTicketRepository.findRoutes(searchRoutesRequest, key, lang);
        checkResultForErrors(routes);
        return routes;
    }

    @Override
    public BusPrice getPrice(PriceRequest priceRequest, String key, String lang) throws APIResponseException, NoSuchResultException, APIResponseClientException {
        BusPrice price = TTNBusTicketRepository.getPrice(priceRequest, key, lang);
        checkResultForErrors(price);
        return price;
    }

    @Override
    public BusCreateBooking createBooking(CreateBookingRequest createBookingRequest, String key, String lang) throws APIResponseException, NoSuchResultException, APIResponseClientException {
        BusCreateBooking createBooking = TTNBusTicketRepository.createBooking(createBookingRequest, key, lang);
        checkResultForErrors(createBooking);
        return createBooking;
    }

    @Override
    public BusRoute getRouteInfo(RouteRequest routeRequest, String key, String lang) throws APIResponseException, NoSuchResultException, APIResponseClientException {
        BusRoute routeInfo = TTNBusTicketRepository.getRouteInfo(routeRequest, key, lang);
        checkResultForErrors(routeInfo);
        return routeInfo;
    }

    @Override
    public BusBookings getBookings(GetBookingsRequest getBookingsRequest, String key, String lang) throws APIResponseException, NoSuchResultException, APIResponseClientException {
        BusBookings bookings = TTNBusTicketRepository.getBookings(getBookingsRequest, key, lang);
        checkResultForErrors(bookings);
        return bookings;
    }

    @Override
    public BusBooking getBookingInfo(GetBookingRequest getBookingRequest, String key, String lang) throws APIResponseException, NoSuchResultException, APIResponseClientException {
        BusBooking bookingInfo = TTNBusTicketRepository.getBookingInfo(getBookingRequest, key, lang);
        checkResultForErrors(bookingInfo);
        return bookingInfo;
    }

    @Override
    public BusBooking getBookingPdfVoucher(GetBookingPdfVoucherRequest getBookingPdfVoucherRequest, String key, String lang) throws APIResponseException, NoSuchResultException, APIResponseClientException {
        BusBooking bookingPdfVoucher = TTNBusTicketRepository.getBookingPdfVoucher(getBookingPdfVoucherRequest, key, lang);
        checkResultForErrors(bookingPdfVoucher);
        return bookingPdfVoucher;
    }

    @Override
    public BusBooking cancelBooking(CancelBookingRequest cancelBookingRequest, String key, String lang) throws APIResponseException, NoSuchResultException, APIResponseClientException {
        BusBooking busBooking = TTNBusTicketRepository.cancelBooking(cancelBookingRequest, key, lang);
        checkResultForErrors(busBooking);
        return busBooking;
    }

    private void checkResultForErrors(Bus bus)
            throws NoSuchResultException, APIResponseClientException {
        log.info(logService + Bracers.createBracers(1), checkResultForErrors);
        Result result = Optional.ofNullable(bus.getResponse().getResult())
                .orElseThrow(NoSuchResultException::new);
        if (!"0".equals(result.getCode())) {
            log.warn(logService + Bracers.createBracers(1), result);
            throw new APIResponseClientException(clientError, result.getCode() + " " + result.getDescription());
        }
    }
}
