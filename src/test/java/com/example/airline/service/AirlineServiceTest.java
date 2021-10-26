package com.example.airline.service;

import com.example.airline.MainTest;
import com.example.airline.model.Aircraft;
import com.example.airline.model.Airline;
import com.example.airline.model.Destination;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AirlineServiceTest extends MainTest {
    static String NAME1 = "Emirates";
    static String NAME2 = "Turkish";
    static String AIRCRAFT_NAME = "Jambo";
    static long BUDGET1 = 15000;
    static long BUDGET2 = 7000;
    @Autowired
    private AirlineService airlineService;

    @Autowired AircraftService aircraftService;

    @Autowired DestinationService destinationService;

    @Order(5)
    @Test
    void initGetAllTest() {
        assertEquals(4, airlineService.getAll().size());
    }
    @Order(10)
    @Test
    void whenAddAirline_airlineGetsId() {
        Airline airline = getAirline();
        assertEquals(0, airline.getId());
        Airline saved = airlineService.save(airline);
        assertNotEquals(0, saved.getId());
        assertEquals(airline.getHomeBase(), saved.getHomeBase());
        assertEquals(airline.getBalance(), saved.getBalance());
        assertEquals(airline.getName(), saved.getName());
    }

    @Order(20)
    @Test
    void whenAskBalanceByName() {
        long actualBudget = airlineService.getBudget(NAME1);
        assertEquals(BUDGET1, actualBudget);
    }

    @Order(30)
    @Test
    void whenAskBalanceByListOfNames() {
        airlineService.save(getAirline2());
        List<String> names = new ArrayList<>(Arrays.asList(NAME1, NAME2));
        Map<String, Long> airlineBudgets = airlineService.getBudget(names);
        assertEquals(airlineBudgets.get(NAME1), BUDGET1);
        assertEquals(airlineBudgets.get(NAME2), BUDGET2);

    }

    @Order(40)
    @Test
    void getAirlineByNameTest() {
        Airline airline = airlineService.getByName(NAME2);
        assertEquals(airline.getBalance(), BUDGET2);
    }

    @Order(50)
    @Test
    void whenAddAirCraft_airCraftListAndBalanceUpdated() {
        Airline airline = airlineService.getByName(NAME1);
        long balance = airline.getBalance();
        Aircraft aircraft = getAircraft();
        Aircraft savedAircraft = aircraftService.save(aircraft);
        long price = aircraft.getPrice();

        airlineService.buy(airline, savedAircraft);
        airlineService.save(airline);

        Airline updatedAirline = airlineService.getByName(NAME1);
        assertEquals(updatedAirline.getBalance(), balance-aircraftService.getSellPrice(savedAircraft));
        //TODO improve test
        assertTrue(containsAircraft(updatedAirline, AIRCRAFT_NAME));
    }

    private boolean containsAircraft(Airline updatedAirline, String aircraftName) {
        Set<Aircraft> aircrafts = updatedAirline.getAircrafts();
        Optional<Aircraft> first = aircrafts.stream().filter(currentAircraft -> aircraftName.equals(currentAircraft.getName())).findFirst();
        return first.isPresent();
    }

    @Order(60)
    @Test
    void whenSellAirCraft_updateOwnersAndBalance(){
        Airline seller = airlineService.getByName(NAME1);
        assertTrue(containsAircraft(seller, AIRCRAFT_NAME));
        Airline buyer = airlineService.getByName(NAME2);
        assertFalse(containsAircraft(buyer, AIRCRAFT_NAME));

        airlineService.sell(seller, buyer, AIRCRAFT_NAME);

        long sellPrice = aircraftService.getSellPrice(AIRCRAFT_NAME);
        Airline updatedSeller = airlineService.getByName(NAME1);
        Airline updatedBuyer = airlineService.getByName(NAME2);

        assertFalse(containsAircraft(updatedSeller, AIRCRAFT_NAME));
        assertTrue(containsAircraft(updatedBuyer, AIRCRAFT_NAME));

        assertEquals(BUDGET1 , seller.getBalance());
        assertEquals(BUDGET2 - sellPrice, buyer.getBalance());
        Aircraft updatedAircraft = aircraftService.getByName(AIRCRAFT_NAME);
        assertEquals(updatedBuyer, updatedAircraft.getAirline());
    }

    @Order(60)
    @Test
    void whenBuyAirCraft_updateOwnersAndBalance(){
        Airline buyer = airlineService.getByName(NAME1);
        assertFalse(containsAircraft(buyer, AIRCRAFT_NAME));
        Airline seller = airlineService.getByName(NAME2);
        assertTrue(containsAircraft(seller, AIRCRAFT_NAME));

        airlineService.buy(buyer, seller, AIRCRAFT_NAME);

        long sellPrice = aircraftService.getSellPrice(AIRCRAFT_NAME);
        Airline updatedSeller = airlineService.getByName(NAME2);
        Airline updatedBuyer = airlineService.getByName(NAME1);

        assertTrue(containsAircraft(updatedBuyer, AIRCRAFT_NAME));
        assertFalse(containsAircraft(updatedSeller, AIRCRAFT_NAME));

        assertEquals(BUDGET2 , seller.getBalance());
        assertEquals(BUDGET1 , buyer.getBalance()+sellPrice);
        Aircraft updatedAircraft = aircraftService.getByName(AIRCRAFT_NAME);
        assertEquals(updatedBuyer, updatedAircraft.getAirline());
    }

    @Order(70)
    @Test
    void whenAddDestination_destinationOnList() {
        Destination destination = destinationService.getByID(12).get();
        Airline airline = airlineService.getByName(NAME1);
        assertFalse(airline.getDestinations().contains(destination));
        assertFalse(destination.getAirlines().contains(airline));
        airlineService.addDestination(airline, destination);

        Airline updatedAirline = airlineService.getByName(NAME1);
        assertTrue(updatedAirline.getDestinations().contains(destination));
        Destination updatedDestination = destinationService.getByID(12).get();
        assertTrue(updatedDestination.getAirlines().contains(updatedAirline));
    }



    private Aircraft getAircraft() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2021);
        calendar.set(Calendar.MONTH, 4);
        calendar.set(Calendar.DATE, 12);
        return new Aircraft(AIRCRAFT_NAME, 340, 3000, calendar.getTime());
    }

    Airline getAirline() {
        Optional<Destination> optDestination = destinationService.getByID(3);
        return new Airline(NAME1, BUDGET1, optDestination.get());
    }

    Airline getAirline2() {
        Optional<Destination> optDestination = destinationService.getByID(5);
        return new Airline(NAME2, BUDGET2, optDestination.get() );
    }


}