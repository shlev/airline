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
    DestinationService destinationService;

    @Order(1)
    @Test
    public void getAllDestinationTest() {
        List<Destination> all = destinationService.getAll();
        assertEquals(all.size(), 100);
    }

    @Order(2)
    @Test
    public void whenGetByID_returnExist() {
        Optional<Destination> optionalDestination = destinationService.getByID(98691);
        assertTrue(optionalDestination.isPresent());
        Destination destination = optionalDestination.get();
        assertEquals("Shanling", destination.getCity());
        assertEquals("China", destination.getCountry());
        assertEquals(113.358645, destination.getLongitude());
        assertEquals(27.582186, destination.getLatitude());
    }

    @Order(3)
    @Test
    public void getDistanceTest() {
        Destination destination1 = destinationService.getByID(6309).get();
        Destination destination2 = destinationService.getByID(60730).get();
        destinationService.getDistance(destination1, destination2);
    }

}