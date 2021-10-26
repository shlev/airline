package com.example.airline.service;

import com.example.airline.model.Aircraft;
import com.example.airline.model.Airline;
import com.example.airline.model.Destination;
import com.example.airline.repository.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AirlineService {

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private AircraftService aircraftService;

    @Autowired
    private DestinationService destinationService;

    private List<Airline> saveAll(List<Airline> airlines) {
        return airlineRepository.saveAll(airlines);
    }

    public Airline save(Airline airline) {
        return airlineRepository.save(airline);
    }

    public List<Airline> getAll() {
        return airlineRepository.findAll();
    }


    public long getBudget(String name) {
        return airlineRepository.findBalanceByName(name);
    }

    public Map<String, Long> getBudget(List<String> names) {
        return names.stream().collect(Collectors.toMap(name ->name, this::getBudget ));
    }

    public Airline getByName(String name) {
        return airlineRepository.findByName(name);
    }

    public void buy(Airline airline, Aircraft aircraft) {
        airline.addAircraft(aircraft);
        long sellPrice = aircraftService.getSellPrice(aircraft);
        airline.setBalance(airline.getBalance()-sellPrice);
    }

    private void sell(Airline seller, Aircraft aircraft) {
        long sellPrice = aircraftService.getSellPrice(aircraft);
        seller.removeAircraft(aircraft);
        seller.setBalance(seller.getBalance()+sellPrice);
    }

    public void sell(Airline seller, Airline buyer, String aircraftName) {
        Aircraft aircraft = aircraftService.getByName(aircraftName);
        sell(seller, aircraft);
        buy(buyer, aircraft);
        aircraftService.save(aircraft);
        this.save(seller);
        this.save(buyer);
    }

    public void buy(Airline buyer, Airline seller, String aircraftName) {
        sell(seller, buyer, aircraftName);
    }

    public void addDestination(Airline airline, Destination destination) {
        airline.addDestination(destination);
        save(airline);
    }

}
