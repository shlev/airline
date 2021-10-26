package com.example.airline.controller;

import com.example.airline.model.Airline;
import com.example.airline.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.openmbean.CompositeData;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class AirlineController {

    @Autowired
    private AirlineService airlineService;

    @PostMapping("/airline")
    public Airline createAirline(@RequestBody Airline airline) {
        return airlineService.save(airline);
    }

    @GetMapping("/airline")
    public List<Airline> getAllAirlines() {
        return airlineService.getAll();
    }
}
