package com.ui.alfabank.alfatravel.bus.repository;

import com.ui.alfabank.alfatravel.bus.entities.requests.*;
import com.ui.alfabank.alfatravel.bus.entities.requests.json.Passenger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class UriBuilder {
    public UriBuilder(Tags tags) {
        this.tags = tags;
    }
    private Tags tags;

    @Value("${provider.TTN.host}")
    private String host;

    @Value("${provider.TTN.json}")
    private String json;

    @Value("${provider.TTN.ep.search-stations}")
    private String searchStationsEP;

    @Value("${provider.TTN.ep.search-routes}")
    private String searchRoutesEP;

    @Value("${provider.TTN.ep.route}")
    private String routeEP;

    @Value("${provider.TTN.ep.search-price}")
    private String searchPriceEP;

    @Value("${provider.TTN.ep.booking}")
    private String bookingEP;

    @Value("${provider.TTN.ep.booking-info}")
    private String bookingInfoEP;

    @Value("${provider.TTN.ep.booking-pdf}")
    private String bookingPdfEP;

    @Value("${provider.TTN.ep.booking-cancel}")
    private String bookingCancelEP;

    @Value("${provider.TTN.pattern.date}")
    private String datePattern;

    public URI createFindStationsURI(SearchStationRequest searchStationRequest, String key, String lang) {
        final String hostAndEP = host + searchStationsEP;
        return getUriComponentsBuilderKeyLang(key, lang, hostAndEP)
                .queryParam(tags.getType(), searchStationRequest.getType())
                .queryParam(tags.getLimit(), searchStationRequest.getLimit())
                .queryParam(tags.getQuery(), searchStationRequest.getQuery())
                .build()
                .encode()
                .toUri();
    }

    public URI createFindRoutesURI(SearchRoutesRequest searchRoutesRequest, String key, String lang) {
        final String hostAndEP = host + searchRoutesEP;
        return getUriComponentsBuilderKeyLang(key, lang, hostAndEP)
                .queryParam(tags.getFrom(), searchRoutesRequest.getFrom())
                .queryParam(tags.getTo(), searchRoutesRequest.getTo())
                .queryParam(tags.getDate(), searchRoutesRequest.getDate().format(DateTimeFormatter.ofPattern(datePattern)))
                .queryParam(tags.getExtendedToken(), searchRoutesRequest.getExtendedToken())
                .build()
                .encode()
                .toUri();
    }

    public URI createGetPriceURI(PriceRequest priceRequest, String key, String lang) {
        final String hostAndEP = host + searchPriceEP;
        final UriComponentsBuilder builder = getRouteCommonFields(key, lang, hostAndEP, priceRequest.getId(), priceRequest.getExtendedToken(), priceRequest.getSessionId());

        return convertPassenger(priceRequest.getPassengers(), builder)
                .build()
                .encode()
                .toUri();
    }

    public URI createBookingURI(CreateBookingRequest createBookingRequest, String key, String lang) {
        final String hostAndEP = host + bookingEP;
        final UriComponentsBuilder builder = getUriComponentsBuilderKeyLang(key, lang, hostAndEP)
                .queryParam(tags.getId(), createBookingRequest.getId())
                .queryParam(tags.getExtendedToken(), createBookingRequest.getExtendedToken())
                .queryParam(tags.getToken(), createBookingRequest.getToken())
                .queryParam(tags.getSessionId(), createBookingRequest.getSessionId());

        return convertPassenger(createBookingRequest.getPassengers(), builder)
                .build()
                .encode()
                .toUri();
    }

    public URI createRouteURI(RouteRequest routeRequest, String key, String lang) {
        final String hostAndEP = host + routeEP;
        return getRouteCommonFields(key, lang, hostAndEP, routeRequest.getId(), routeRequest.getExtendedToken(), routeRequest.getSessionId())
                .build()
                .encode()
                .toUri();
    }

    public URI createGetBookingsURI(GetBookingsRequest getBookingsRequest, String key, String lang) {
        final String hostAndEP = host + bookingEP;
        return getBookingCommonFields(key, lang, hostAndEP, getBookingsRequest.getExtendedToken(), getBookingsRequest.getToken());
    }

    public URI createGetBookingURI(GetBookingRequest getBookingRequest, String key, String lang) {
        final String hostAndEP = host + bookingInfoEP + getBookingRequest.getReservationId() + json;
        return getBookingCommonFields(key, lang, hostAndEP, getBookingRequest.getExtendedToken(), getBookingRequest.getToken());
    }

    public URI createGetBookingPdfVoucherURI(GetBookingPdfVoucherRequest getBookingPdfVoucherRequest, String key, String lang) {
        final String hostAndEP = host + bookingInfoEP + bookingPdfEP + getBookingPdfVoucherRequest.getReservationId() + json;
        return getBookingCommonFields(key, lang, hostAndEP, getBookingPdfVoucherRequest.getExtendedToken(), getBookingPdfVoucherRequest.getToken());
    }

    public URI createCancelBookingURI(CancelBookingRequest cancelBookingRequest, String key, String lang) {
        final String hostAndEP = host + bookingInfoEP + bookingCancelEP + cancelBookingRequest.getReservationId() + json;
        return getBookingCommonFields(key, lang, hostAndEP, cancelBookingRequest.getExtendedToken(), cancelBookingRequest.getToken());
    }

    private UriComponentsBuilder getUriComponentsBuilderKeyLang(String key, String lang, String hostAndEP) {
        return UriComponentsBuilder.fromUriString(hostAndEP)
                .queryParam(tags.getKey(), key)
                .queryParam(tags.getLang(), lang);
    }

    private UriComponentsBuilder convertPassenger(List<Passenger> passengers, UriComponentsBuilder builder) {
        IntStream.range(0, passengers.size())
                .forEach(i -> {
                    builder.queryParam(destinationBracers(tags.getBirthday(), i), passengers.get(i).getBirthday());
                    builder.queryParam(destinationBracers(tags.getCitizenship(), i), passengers.get(i).getCitizenship());
                    builder.queryParam(destinationBracers(tags.getDiscountId(), i), passengers.get(i).getDiscountId());
                    builder.queryParam(destinationBracers(tags.getDocumentNumber(), i), passengers.get(i).getDocumentNumber());
                    builder.queryParam(destinationBracers(tags.getDocumentType(), i), passengers.get(i).getDocumentType());
                    builder.queryParam(destinationBracers(tags.getGender(), i), passengers.get(i).getGender());
                    builder.queryParam(destinationBracers(tags.getMiddleName(), i), passengers.get(i).getMidlename());
                    builder.queryParam(destinationBracers(tags.getName(), i), passengers.get(i).getName());
                    builder.queryParam(destinationBracers(tags.getPlaceNumber(), i), passengers.get(i).getPlaceNumber());
                    builder.queryParam(destinationBracers(tags.getSurname(), i), passengers.get(i).getSurname());
                });
        return builder;
    }

    private UriComponentsBuilder getRouteCommonFields(String key, String lang, String hostAndEP, String id, String extendedToken, String sessionId) {
        return getUriComponentsBuilderKeyLang(key, lang, hostAndEP)
                .queryParam(tags.getId(), id)
                .queryParam(tags.getExtendedToken(), extendedToken)
                .queryParam(tags.getSessionId(), sessionId);
    }

    private String destinationBracers(String s, int index) {
        return tags.getPassenger() + "[" + index + "]" + "[" + s + "]";
    }

    private URI getBookingCommonFields(String key, String lang, String hostAndEP, String extendedToken, String token) {
        return getUriComponentsBuilderKeyLang(key, lang, hostAndEP)
                .queryParam(tags.getExtendedToken(), extendedToken)
                .queryParam(tags.getToken(), token)
                .build()
                .encode()
                .toUri();
    }
}
