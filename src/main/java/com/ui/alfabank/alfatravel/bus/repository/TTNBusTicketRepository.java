package com.ui.alfabank.alfatravel.bus.repository;

import com.ui.alfabank.alfatravel.bus.entities.exceptions.APIResponseException;
import com.ui.alfabank.alfatravel.bus.entities.providerResponses.*;
import com.ui.alfabank.alfatravel.bus.entities.requests.*;
import com.ui.alfabank.alfatravel.bus.util.Bracers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Optional;

@Slf4j
@Repository
public class TTNBusTicketRepository {
    @Value("${msg.repository.logUri}")
    private String logUri;
    @Value("${msg.repository.logResponse}")
    private String logResponse;

    private UriBuilder uriBuilder;
    private RestTemplate restTemplate;

    public TTNBusTicketRepository(UriBuilder uriBuilder, RestTemplate restTemplate) {
        this.uriBuilder = uriBuilder;
        this.restTemplate = restTemplate;
    }

    private <T extends Bus> T templateResponse(URI uri, Class<T> responseType) throws APIResponseException {
        log.info(logUri + Bracers.createBracers(1), uri);
        try {
            return Optional.ofNullable(restTemplate.getForEntity(uri, responseType).getBody())
                    .orElseThrow(() -> new APIResponseException(HttpStatus.BAD_GATEWAY));
        } catch (HttpClientErrorException e) {
            log.warn(logResponse + Bracers.createBracers(1), e.getMessage());
            throw new APIResponseException(e.getStatusCode());
        } catch (RestClientException e) {
            log.warn(logResponse + Bracers.createBracers(1), e.getMessage());
            throw new APIResponseException(HttpStatus.NOT_FOUND);
        }
    }

    public BusSearchStations findStations(SearchStationRequest searchStationRequest, String key, String lang) throws APIResponseException {
        final URI uri = uriBuilder.createFindStationsURI(searchStationRequest, key, lang);
        return templateResponse(uri, BusSearchStations.class);
    }

    public BusSearchRoutes findRoutes(SearchRoutesRequest searchRoutesRequest, String key, String lang) throws APIResponseException {
        final URI uri = uriBuilder.createFindRoutesURI(searchRoutesRequest, key, lang);
        return templateResponse(uri, BusSearchRoutes.class);
    }

    public BusPrice getPrice(PriceRequest priceRequest, String key, String lang) throws APIResponseException {
        final URI uri = uriBuilder.createGetPriceURI(priceRequest, key, lang);
        return templateResponse(uri, BusPrice.class);
    }

    public BusCreateBooking createBooking(CreateBookingRequest createBookingRequest, String key, String lang) throws APIResponseException {
        final URI uri = uriBuilder.createBookingURI(createBookingRequest, key, lang);
        return templateResponse(uri, BusCreateBooking.class);
    }

    public BusRoute getRouteInfo(RouteRequest routeRequest, String key, String lang) throws APIResponseException {
        final URI uri = uriBuilder.createRouteURI(routeRequest, key, lang);
        return templateResponse(uri, BusRoute.class);
    }

    public BusBookings getBookings(GetBookingsRequest getBookingsRequest, String key, String lang) throws APIResponseException {
        final URI uri = uriBuilder.createGetBookingsURI(getBookingsRequest, key, lang);
        return templateResponse(uri, BusBookings.class);
    }

    public BusBooking getBookingInfo(GetBookingRequest getBookingRequest, String key, String lang) throws APIResponseException {
        final URI uri = uriBuilder.createGetBookingURI(getBookingRequest, key, lang);
        return templateResponse(uri, BusBooking.class);
    }

    public BusBooking getBookingPdfVoucher(GetBookingPdfVoucherRequest getBookingPdfVoucherRequest, String key, String lang) throws APIResponseException {
        final URI uri = uriBuilder.createGetBookingPdfVoucherURI(getBookingPdfVoucherRequest, key, lang);
        return  templateResponse(uri, BusBooking.class); // TODO узнать какой будет ответ, пока копипаст
    }

    public BusBooking cancelBooking(CancelBookingRequest cancelBookingRequest, String key, String lang) throws APIResponseException {
        final URI uri = uriBuilder.createCancelBookingURI(cancelBookingRequest, key, lang);
        return templateResponse(uri, BusBooking.class); // TODO узнать какой будет ответ, пока копипаст
    }
}
