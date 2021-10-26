package com.example.airline.service;

import com.example.airline.MainTest;
import com.example.airline.model.Aircraft;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AircraftServiceTest extends MainTest {

    static final String AIRCRAFT_NAME = "Air1";
    @Autowired
    AircraftService aircraftService;



    @Order(1)
    @Test
    void serviceNotNullTest() {
        assertNotNull(aircraftService);
    }

    @Order(2)
    @Test
    void whenSaved_thenReturn() {
        aircraftService.save(getAirCraft1());
        int actual = aircraftService.getAll().size();
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Order(3)
    @Test
    void getSellPriceTest() {
        Aircraft aircraft = aircraftService.getByName(AIRCRAFT_NAME);
        Calendar calendar= Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2018);
        calendar.set(Calendar.MONTH, 3);
        calendar.set(Calendar.DAY_OF_MONTH,23);
        aircraft.setStartedAt(calendar.getTime());
        long sellPrice = aircraftService.getSellPrice(aircraft, new Date());
        assertEquals(160, sellPrice);
    }

    private Aircraft getAirCraft1() {
        return new Aircraft(AIRCRAFT_NAME, 1000,300 );
    }
}