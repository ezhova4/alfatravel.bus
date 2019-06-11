package com.ui.alfabank.alfatravel.bus.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ui.alfabank.alfatravel.bus.entities.error.ErrorResponse;
import com.ui.alfabank.alfatravel.bus.entities.exceptions.APIResponseException;
import com.ui.alfabank.alfatravel.bus.entities.providerResponses.*;
import com.ui.alfabank.alfatravel.bus.entities.requests.*;
import com.ui.alfabank.alfatravel.bus.service.TTNBusTicketService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.File;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = BusTicketController.class)
public class BusTicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TTNBusTicketService busTicketService;

    @Value("${provider.TTN.header.key}")
    private String key;

    @Value("${provider.TTN.header.lang}")
    private String lang;

    private ErrorResponse errorResponse;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        errorResponse = ErrorResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.toString())
                .error(ResponseStatusCode.BAD_RESPONSE.toString())
                .message("Test APIResponseException!")
                .build();
    }



    @Test
    public void searchStationsWhenQueryStringReturnEqualsNameRu() throws Exception {
        SearchStationRequest searchStationRequest = getSearchStationRequest();
        BusSearchStations busSearchStations = objectMapper.readValue(new File("src/test/resources/search-station-response.json"), BusSearchStations.class);

        getSearchStationsWhen(searchStationRequest).thenReturn(busSearchStations);
        doSearchStationsTest(searchStationRequest, busSearchStations)
                .andExpect(jsonPath("$.response.stations[0].name_ru", equalToIgnoringCase(searchStationRequest.getQuery())));
    }

    @Test
    public void searchStationsWhenAnyRequestReturnException() throws Exception {
        SearchStationRequest searchStationRequest = getSearchStationRequest();
        getSearchStationsWhen(searchStationRequest).thenThrow(new APIResponseException("Test APIResponseException!"));
        doSearchStationsTest(searchStationRequest, errorResponse);
    }

    private <U> ResultActions doSearchStationsTest(SearchStationRequest searchStationRequest, U response) throws Exception {
        return doTest(searchStationRequest, response, "/stations");
    }

    private OngoingStubbing<BusSearchStations> getSearchStationsWhen(SearchStationRequest searchStationRequest) throws APIResponseException {
        return when(busTicketService.findStations(eq(searchStationRequest), isA(String.class), isA(String.class)));
    }

    private SearchStationRequest getSearchStationRequest() throws java.io.IOException {
        return objectMapper.readValue(new File("src/test/resources/search-station-request.json"), SearchStationRequest.class);
    }

    @Test
    public void searchRoutesWhenRequestDepartureDateEqualsResponseDepartureDate() throws Exception {
        SearchRoutesRequest searchRoutesRequest = getSearchRoutesRequest();
        BusSearchRoutes busSearchRoutes = objectMapper.readValue(new File("src/test/resources/search-routes-response.json"), BusSearchRoutes.class);
        String formatDate = searchRoutesRequest.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        getSearchRoutesWhen(searchRoutesRequest).thenReturn(busSearchRoutes);
        doSearchRoutesTest(searchRoutesRequest, busSearchRoutes)
                .andExpect(jsonPath("$.response.buses[1].departure_date", equalToIgnoringCase(formatDate)));
    }

    @Test
    public void searchRoutesWhenAnyRequestReturnException() throws Exception {
        SearchRoutesRequest searchRoutesRequest = getSearchRoutesRequest();
        getSearchRoutesWhen(searchRoutesRequest).thenThrow(new APIResponseException("Test APIResponseException!"));
        doSearchRoutesTest(searchRoutesRequest, errorResponse);
    }

    private <U> ResultActions doSearchRoutesTest(SearchRoutesRequest searchRoutesRequest, U response) throws Exception {
        return doTest(searchRoutesRequest, response, "/routes");
    }

    private OngoingStubbing<BusSearchRoutes> getSearchRoutesWhen(SearchRoutesRequest searchRoutesRequest) throws APIResponseException {
        return when(busTicketService.findRoutes(eq(searchRoutesRequest), isA(String.class), isA(String.class)));
    }

    private SearchRoutesRequest getSearchRoutesRequest() throws java.io.IOException {
        return objectMapper.readValue(new File("src/test/resources/search-routes-request.json"), SearchRoutesRequest.class);
    }

    @Test
    public void getPriceWhenRequestSessionIdEqualsResponse() throws Exception {
        PriceRequest priceRequest = getPriceRequest();
        BusPrice busPrice = objectMapper.readValue(new File("src/test/resources/get-price-response.json"), BusPrice.class);

        getGetPriceWhen(priceRequest).thenReturn(busPrice);
        doGetPriceTest(priceRequest, busPrice)
                .andExpect(jsonPath("$.response.session_id", equalToIgnoringCase(priceRequest.getSessionId())));
    }

    @Test
    public void getPriceWhenAnyRequestReturnException() throws Exception {
        PriceRequest priceRequest = getPriceRequest();

        getGetPriceWhen(priceRequest).thenThrow(new APIResponseException("Test APIResponseException!"));
        doGetPriceTest(priceRequest, errorResponse);

    }

    private <U> ResultActions doGetPriceTest(PriceRequest priceRequest, U response) throws Exception {
        return doTest(priceRequest, response, "/price");
    }

    private OngoingStubbing<BusPrice> getGetPriceWhen(PriceRequest priceRequest) throws APIResponseException {
        return when(busTicketService.getPrice(eq(priceRequest), isA(String.class), isA(String.class)));
    }

    private PriceRequest getPriceRequest() throws java.io.IOException {
        return objectMapper.readValue(new File("src/test/resources/get-price-request.json"), PriceRequest.class);
    }

    @Test
    public void createBookingWhenRequestDocumentInfoEqualsResponse() throws Exception {
        CreateBookingRequest createBookingRequest = getCreateBookingRequest();
        BusCreateBooking busCreateBooking = objectMapper.readValue(new File("src/test/resources/create-booking-response.json"), BusCreateBooking.class);
        String passengerInfo = new StringBuilder()
                .append(createBookingRequest.getPassengers().get(0).getSurname())
                .append(" ")
                .append(createBookingRequest.getPassengers().get(0).getName())
                .toString();

        getCreateBookingWhen(createBookingRequest).thenReturn(busCreateBooking);
        doCreateBookingTest(createBookingRequest, busCreateBooking)
                .andExpect(jsonPath("$.response.session_id", equalToIgnoringCase(createBookingRequest.getSessionId())))
                .andExpect(jsonPath("$.response.booking.documents[0].passenger_info", equalToIgnoringCase(passengerInfo)))
                .andExpect(jsonPath("$.response.booking.documents[0].place_number", equalToIgnoringCase(String.valueOf(createBookingRequest.getPassengers().get(0).getPlaceNumber()))));
    }

    @Test
    public void createBookingWhenAnyRequestReturnException() throws Exception {
        CreateBookingRequest createBookingRequest = getCreateBookingRequest();
        getCreateBookingWhen(createBookingRequest).thenThrow(new APIResponseException("Test APIResponseException!"));
        doCreateBookingTest(createBookingRequest, errorResponse);
    }

    private <U> ResultActions doCreateBookingTest(CreateBookingRequest createBookingRequest, U response) throws Exception {
        return doTest(createBookingRequest, response, "/createBooking");
    }

    private OngoingStubbing<BusCreateBooking> getCreateBookingWhen(CreateBookingRequest createBookingRequest) throws APIResponseException {
        return when(busTicketService.createBooking(eq(createBookingRequest), isA(String.class), isA(String.class)));
    }

    private CreateBookingRequest getCreateBookingRequest() throws java.io.IOException {
        return objectMapper.readValue(new File("src/test/resources/create-booking-request.json"), CreateBookingRequest.class);
    }

    @Test
    public void getRouteInfoWhenRequestRouteIdAndSessionIdEqualsResponse() throws Exception {
        RouteRequest routeRequest = getRouteRequest();
        BusRoute busRoute = objectMapper.readValue(new File("src/test/resources/get-route-info-response.json"), BusRoute.class);

        getGetRouteInfoWhen(routeRequest).thenReturn(busRoute);
        doGetRouteInfoTest(routeRequest, busRoute)
                .andExpect(jsonPath("$.response.session_id", equalToIgnoringCase(routeRequest.getSessionId())))
                .andExpect(jsonPath("$.response.bus.id", equalToIgnoringCase(routeRequest.getId())));
    }

    @Test
    public void getRouteInfoWhenAnyRequestReturnException() throws Exception {
        RouteRequest routeRequest = getRouteRequest();
        getGetRouteInfoWhen(routeRequest).thenThrow(new APIResponseException("Test APIResponseException!"));
        doGetRouteInfoTest(routeRequest, errorResponse);
    }

    private <U> ResultActions doGetRouteInfoTest(RouteRequest routeRequest, U response) throws Exception {
        return doTest(routeRequest, response, "/route");
    }

    private OngoingStubbing<BusRoute> getGetRouteInfoWhen(RouteRequest routeRequest) throws APIResponseException {
        return when(busTicketService.getRouteInfo(eq(routeRequest), isA(String.class), isA(String.class)));
    }

    private RouteRequest getRouteRequest() throws java.io.IOException {
        return objectMapper.readValue(new File("src/test/resources/get-route-info-request.json"), RouteRequest.class);
    }

    @Test
    public void getBookingsWhenRequestTokenIdAccordingResponseReservationId() throws Exception {
        GetBookingsRequest getBookingsRequest = getGetBookingsRequest();
        BusBookings busBookings = objectMapper.readValue(new File("src/test/resources/get-bookings-response.json"), BusBookings.class);
        getBookingsRequest.setToken("YTBmZjY4MjctMDM3Ny00N2Q0LTkzMTQtYmNmZmQ3OWY3NjQy");

        getGetBookingsWhen(getBookingsRequest).thenReturn(busBookings);
        doGetBookingsTest(getBookingsRequest, busBookings)
                .andExpect(jsonPath("$.response.bookings[0].reservation_id", equalToIgnoringCase("SCZXLQFBRG")));
    }
    @Test
    public void getBookingsWhenAnyRequestReturnException() throws Exception {
        GetBookingsRequest getBookingsRequest = getGetBookingsRequest();
        getGetBookingsWhen(getBookingsRequest).thenThrow(new APIResponseException("Test APIResponseException!"));
        doGetBookingsTest(getBookingsRequest, errorResponse);
    }

    private GetBookingsRequest getGetBookingsRequest() throws java.io.IOException {
        return objectMapper.readValue(new File("src/test/resources/get-bookings-request.json"), GetBookingsRequest.class);
    }

    private <T> ResultActions doGetBookingsTest(GetBookingsRequest getBookingsRequest, T response) throws Exception {
        return doTest(getBookingsRequest, response, "/bookings");
    }

    private OngoingStubbing<BusBookings> getGetBookingsWhen(GetBookingsRequest getBookingsRequest) throws APIResponseException {
        return when(busTicketService.getBookings(eq(getBookingsRequest), isA(String.class), isA(String.class)));
    }

    @Test
    public void getBookingInfoWhenRequestReservationIdEqualsResponse() throws Exception {
        GetBookingRequest getBookingRequest = getGetBookingRequest();
        BusBooking busBooking = objectMapper.readValue(new File("src/test/resources/get-booking-info-response.json"), BusBooking.class);

        getGetBookingInfoWhen(getBookingRequest).thenReturn(busBooking);
        doGetBookingTest(getBookingRequest, busBooking)
                .andExpect(jsonPath("$.response.booking.reservation_id", equalToIgnoringCase(getBookingRequest.getReservationId())));
    }

    @Test
    public void getBookingInfoWhenAnyRequestReturnException() throws Exception {
        GetBookingRequest getBookingRequest = getGetBookingRequest();
        getGetBookingInfoWhen(getBookingRequest).thenThrow(new APIResponseException("Test APIResponseException!"));
        doGetBookingTest(getBookingRequest, errorResponse);
    }

    private <T> ResultActions doGetBookingTest(GetBookingRequest getBookingRequest, T response) throws Exception {
        return doTest(getBookingRequest, response, "/booking");
    }

    private OngoingStubbing<BusBooking> getGetBookingInfoWhen(GetBookingRequest getBookingRequest) throws APIResponseException {
        return when(busTicketService.getBookingInfo(eq(getBookingRequest), isA(String.class), isA(String.class)));
    }

    private GetBookingRequest getGetBookingRequest() throws java.io.IOException {
        return objectMapper.readValue(new File("src/test/resources/get-booking-info-request.json"), GetBookingRequest.class);
    }

    private <T extends Request, U> ResultActions doTest(T request, U response, String endPoint) throws Exception {
        String keyValue = "77f59529-cbb9-4a06-814b-b3850ad1ba76";
        String langValue = "";
        return mockMvc
                .perform(post(endPoint)
                        .header(key, keyValue)
                        .header(lang, langValue)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }
}