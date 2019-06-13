package com.ui.alfabank.alfatravel.bus.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ui.alfabank.alfatravel.bus.entities.error.ErrorResponse;
import com.ui.alfabank.alfatravel.bus.entities.exceptions.APIResponseClientException;
import com.ui.alfabank.alfatravel.bus.entities.exceptions.APIResponseException;
import com.ui.alfabank.alfatravel.bus.entities.exceptions.NoSuchResultException;
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

    private final String searchStationResponseFile = "src/test/resources/search-station-response.json";
    private final String searchStationRequestFile = "src/test/resources/search-station-request.json";
    private final String searchRoutesResponseFile = "src/test/resources/search-routes-response.json";
    private final String searchRoutesRequestFile = "src/test/resources/search-routes-request.json";
    private final String getPriceResponseFile = "src/test/resources/get-price-response.json";
    private final String getPriceRequestFile = "src/test/resources/get-price-request.json";
    private final String createBookingResponseFile = "src/test/resources/create-booking-response.json";
    private final String createBookingRequestFile = "src/test/resources/create-booking-request.json";
    private final String getRouteInfoResponseFile = "src/test/resources/get-route-info-response.json";
    private final String getRouteInfoRequestFile = "src/test/resources/get-route-info-request.json";
    private final String getBookingsResponseFile = "src/test/resources/get-bookings-response.json";
    private final String getBookingsRequestFile = "src/test/resources/get-bookings-request.json";
    private final String getBookingInfoResponseFile = "src/test/resources/get-booking-info-response.json";
    private final String getBookingInfoRequestFile = "src/test/resources/get-booking-info-request.json";

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        errorResponse = ErrorResponse.builder()
                .statusCode(HttpStatus.BAD_GATEWAY.toString())
                .error(ResponseStatusCode.BAD_REQUEST.toString())
                .message(HttpStatus.BAD_GATEWAY.getReasonPhrase())
                .build();
    }

// TODO Сделать еще тесты на получние HttpMessageNotReadableException, APIResponseClientException, MethodArgumentNotValidException

    @Test
    public void searchStationsWhenQueryStringReturnEqualsNameRu() throws Exception {
        SearchStationRequest searchStationRequest = getObjectFromJsonFile(searchStationRequestFile, SearchStationRequest.class);
        BusSearchStations busSearchStations = getObjectFromJsonFile(searchStationResponseFile, BusSearchStations.class);

        getSearchStationsOngoingStubbing(searchStationRequest).thenReturn(busSearchStations);
        doTest(searchStationRequest, busSearchStations, "/stations")
                .andExpect(jsonPath("$.response.stations[0].name_ru", equalToIgnoringCase(searchStationRequest.getQuery())));
    }

    @Test
    public void searchStationsWhenAnyRequestReturnAPIResponseException() throws Exception {
        SearchStationRequest searchStationRequest = getObjectFromJsonFile(searchStationRequestFile, SearchStationRequest.class);
        getSearchStationsOngoingStubbing(searchStationRequest).thenThrow(new APIResponseException(HttpStatus.BAD_GATEWAY));
        doTest(searchStationRequest, errorResponse, "/stations");
    }

    private OngoingStubbing<BusSearchStations> getSearchStationsOngoingStubbing(SearchStationRequest searchStationRequest) throws APIResponseException, NoSuchResultException, APIResponseClientException {
        return when(busTicketService.findStations(eq(searchStationRequest), isA(String.class), isA(String.class)));
    }

    @Test
    public void searchRoutesWhenRequestDepartureDateEqualsResponseDepartureDate() throws Exception {
        SearchRoutesRequest searchRoutesRequest = getObjectFromJsonFile(searchRoutesRequestFile, SearchRoutesRequest.class);
        BusSearchRoutes busSearchRoutes = getObjectFromJsonFile(searchRoutesResponseFile, BusSearchRoutes.class);

        getSearchRoutesOngoingStubbing(searchRoutesRequest).thenReturn(busSearchRoutes);
        doTest(searchRoutesRequest, busSearchRoutes, "/routes")
                .andExpect(jsonPath("$.response.buses[1].departure_date", equalToIgnoringCase(searchRoutesRequest.getDate().toString())));
    }

    @Test
    public void searchRoutesWhenAnyRequestReturnAPIResponseException() throws Exception {
        SearchRoutesRequest searchRoutesRequest = getObjectFromJsonFile(searchRoutesRequestFile, SearchRoutesRequest.class);
        getSearchRoutesOngoingStubbing(searchRoutesRequest).thenThrow(new APIResponseException(HttpStatus.BAD_GATEWAY));
        doTest(searchRoutesRequest, errorResponse, "/routes");
    }

    private OngoingStubbing<BusSearchRoutes> getSearchRoutesOngoingStubbing(SearchRoutesRequest searchRoutesRequest) throws APIResponseException, NoSuchResultException, APIResponseClientException {
        return when(busTicketService.findRoutes(eq(searchRoutesRequest), isA(String.class), isA(String.class)));
    }

    @Test
    public void getPriceWhenRequestSessionIdEqualsResponse() throws Exception {
        PriceRequest priceRequest = getObjectFromJsonFile(getPriceRequestFile, PriceRequest.class);
        BusPrice busPrice = getObjectFromJsonFile(getPriceResponseFile, BusPrice.class);

        getGetPriceOngoingStubbing(priceRequest).thenReturn(busPrice);
        doTest(priceRequest, busPrice, "/price")
                .andExpect(jsonPath("$.response.session_id", equalToIgnoringCase(priceRequest.getSessionId())));
    }

    @Test
    public void getPriceWhenAnyRequestReturnAPIResponseException() throws Exception {
        PriceRequest priceRequest = getObjectFromJsonFile(getPriceRequestFile, PriceRequest.class);

        getGetPriceOngoingStubbing(priceRequest).thenThrow(new APIResponseException(HttpStatus.BAD_GATEWAY));
        doTest(priceRequest, errorResponse, "/price");

    }

    private OngoingStubbing<BusPrice> getGetPriceOngoingStubbing(PriceRequest priceRequest) throws APIResponseException, NoSuchResultException, APIResponseClientException {
        return when(busTicketService.getPrice(eq(priceRequest), isA(String.class), isA(String.class)));
    }

    @Test
    public void createBookingWhenRequestDocumentInfoEqualsResponse() throws Exception {
        CreateBookingRequest createBookingRequest = getObjectFromJsonFile(createBookingRequestFile, CreateBookingRequest.class);
        BusCreateBooking busCreateBooking = getObjectFromJsonFile(createBookingResponseFile, BusCreateBooking.class);
        String passengerInfo = new StringBuilder()
                .append(createBookingRequest.getPassengers().get(0).getSurname())
                .append(" ")
                .append(createBookingRequest.getPassengers().get(0).getName())
                .toString();

        getCreateBookingOngoingStubbing(createBookingRequest).thenReturn(busCreateBooking);
        doTest(createBookingRequest, busCreateBooking, "/createBooking")
                .andExpect(jsonPath("$.response.session_id", equalToIgnoringCase(createBookingRequest.getSessionId())))
                .andExpect(jsonPath("$.response.booking.documents[0].passenger_info", equalToIgnoringCase(passengerInfo)))
                .andExpect(jsonPath("$.response.booking.documents[0].place_number", equalToIgnoringCase(String.valueOf(createBookingRequest.getPassengers().get(0).getPlaceNumber()))));
    }

    @Test
    public void createBookingWhenAnyRequestReturnAPIResponseException() throws Exception {
        CreateBookingRequest createBookingRequest = getObjectFromJsonFile(createBookingRequestFile, CreateBookingRequest.class);
        getCreateBookingOngoingStubbing(createBookingRequest).thenThrow(new APIResponseException(HttpStatus.BAD_GATEWAY));
        doTest(createBookingRequest, errorResponse, "/createBooking");
    }

    private OngoingStubbing<BusCreateBooking> getCreateBookingOngoingStubbing(CreateBookingRequest createBookingRequest) throws APIResponseException, NoSuchResultException, APIResponseClientException {
        return when(busTicketService.createBooking(eq(createBookingRequest), isA(String.class), isA(String.class)));
    }

    @Test
    public void getRouteInfoWhenRequestRouteIdAndSessionIdEqualsResponse() throws Exception {
        RouteRequest routeRequest = getObjectFromJsonFile(getRouteInfoRequestFile, RouteRequest.class);
        BusRoute busRoute = getObjectFromJsonFile(getRouteInfoResponseFile, BusRoute.class);

        getGetRouteInfoOngoingStubbing(routeRequest).thenReturn(busRoute);
        doTest(routeRequest, busRoute, "/routeInfo")
                .andExpect(jsonPath("$.response.session_id", equalToIgnoringCase(routeRequest.getSessionId())))
                .andExpect(jsonPath("$.response.bus.id", equalToIgnoringCase(routeRequest.getId())));
    }

    @Test
    public void getRouteInfoWhenAnyRequestReturnAPIResponseException() throws Exception {
        RouteRequest routeRequest = getObjectFromJsonFile(getRouteInfoRequestFile, RouteRequest.class);
        getGetRouteInfoOngoingStubbing(routeRequest).thenThrow(new APIResponseException(HttpStatus.BAD_GATEWAY));
        doTest(routeRequest, errorResponse, "/routeInfo");
    }

    private OngoingStubbing<BusRoute> getGetRouteInfoOngoingStubbing(RouteRequest routeRequest) throws APIResponseException, NoSuchResultException, APIResponseClientException {
        return when(busTicketService.getRouteInfo(eq(routeRequest), isA(String.class), isA(String.class)));
    }

    @Test
    public void getBookingsWhenRequestTokenIdAccordingResponseReservationId() throws Exception {
        GetBookingsRequest getBookingsRequest = getObjectFromJsonFile(getBookingsRequestFile, GetBookingsRequest.class);
        BusBookings busBookings = getObjectFromJsonFile(getBookingsResponseFile, BusBookings.class);

        getGetBookingsOngoingStubbing(getBookingsRequest).thenReturn(busBookings);
        getBookingsRequest.setToken("YTBmZjY4MjctMDM3Ny00N2Q0LTkzMTQtYmNmZmQ3OWY3NjQy");
        doTest(getBookingsRequest, busBookings, "/bookings")
                .andExpect(jsonPath("$.response.bookings[0].reservation_id", equalToIgnoringCase("SCZXLQFBRG")));
    }
    @Test
    public void getBookingsWhenAnyRequestReturnAPIResponseException() throws Exception {
        GetBookingsRequest getBookingsRequest = getObjectFromJsonFile(getBookingsRequestFile, GetBookingsRequest.class);
        getGetBookingsOngoingStubbing(getBookingsRequest).thenThrow(new APIResponseException(HttpStatus.BAD_GATEWAY));
        doTest(getBookingsRequest, errorResponse, "/bookings");
    }

    private OngoingStubbing<BusBookings> getGetBookingsOngoingStubbing(GetBookingsRequest getBookingsRequest) throws APIResponseException, NoSuchResultException, APIResponseClientException {
        return when(busTicketService.getBookings(eq(getBookingsRequest), isA(String.class), isA(String.class)));
    }

    @Test
    public void getBookingInfoWhenRequestReservationIdEqualsResponse() throws Exception {
        GetBookingRequest getBookingRequest = getObjectFromJsonFile(getBookingInfoRequestFile, GetBookingRequest.class);
        BusBooking busBooking = getObjectFromJsonFile(getBookingInfoResponseFile, BusBooking.class);

        getGetBookingInfoOngoingStubbing(getBookingRequest).thenReturn(busBooking);
        doTest(getBookingRequest, busBooking, "/bookingInfo")
                .andExpect(jsonPath("$.response.booking.reservation_id", equalToIgnoringCase(getBookingRequest.getReservationId())));
    }

    @Test
    public void getBookingInfoWhenAnyRequestReturnAPIResponseException() throws Exception {
        GetBookingRequest getBookingRequest = getObjectFromJsonFile(getBookingInfoRequestFile, GetBookingRequest.class);
        getGetBookingInfoOngoingStubbing(getBookingRequest).thenThrow(new APIResponseException(HttpStatus.BAD_GATEWAY));
        doTest(getBookingRequest, errorResponse, "/bookingInfo");
    }

    private OngoingStubbing<BusBooking> getGetBookingInfoOngoingStubbing(GetBookingRequest getBookingRequest) throws APIResponseException, NoSuchResultException, APIResponseClientException {
        return when(busTicketService.getBookingInfo(eq(getBookingRequest), isA(String.class), isA(String.class)));
    }

    private <T> T getObjectFromJsonFile(String requestFile, Class<T> tClass) throws java.io.IOException {
        return objectMapper.readValue(new File(requestFile), tClass);
    }

    private <T extends Request, U> ResultActions doTest(T request, U response, String endPoint) throws Exception {
        String keyValue = "77f59529-cbb9-4a06-814b-b3850ad1ba76";
        String langValue = "en";
        return mockMvc
                .perform(post("/bus" + endPoint)
                        .header(key, keyValue)
                        .header(lang, langValue)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }
}