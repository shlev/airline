package com.example.airline.service;

import com.example.airline.MainTest;
import com.example.airline.model.Destination;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DestinationServiceTest extends MainTest {

    @Autowired
    DestinationServiceImpl destinationService;

    @Order(1)
    @Test
    public void getAllDestinationTest() {
        List<Destination> all = destinationService.getAll();
        assertEquals(20, all.size());
    }

    @Order(2)
    @Test
    public void whenGetByID_returnExist() {
        Destination destination = destinationService.getById(3L);
        assertEquals("Maasin", destination.getCity());
        assertEquals("Philippines", destination.getCountry());
        assertEquals(124.841371, destination.getLongitude());
        assertEquals(10.132503, destination.getLatitude());
    }

//    https://www.geodatasource.com/distance-calculator
    @Order(3)
    @Test
    public void getDistanceTest() {
        Destination destination1 = destinationService.getById(4L);
        Destination destination2 = destinationService.getById(5L);
        double distance = destinationService.getDistanceGeoCalc(destination1, destination2);
        double expected = 4183.79;
        boolean inRange = Math.abs(expected - distance) < 10;
        assertTrue(inRange);
    }



}