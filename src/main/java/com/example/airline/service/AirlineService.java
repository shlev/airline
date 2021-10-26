package com.example.airline.service;

import com.example.airline.model.Aircraft;
import com.example.airline.model.Airline;
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

//    @PostConstruct
//    private void init() {
//        Airline elal = new Airline("ElAl", 4000, "Tel-Aviv Israel");
//        Airline qatarAirlines = new Airline("Qatar Airlines", 12000, "Doha Qatar");
//        Airline turkishAirlines = new Airline("Turkish Airlines", 8000, "Istanbul Turkey");
//        List<Airline> airlines = new ArrayList<>(Arrays.asList(elal, qatarAirlines, turkishAirlines));
//        saveAll(airlines);
//
//        Aircraft aircraft = new Aircraft("Airplane", 100, 200);
//        aircraftService.save(aircraft);
//        buy(qatarAirlines, aircraft);
//        save(qatarAirlines);
//    }

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
        return names.stream().collect(Collectors.toMap(name ->name, name->getBudget(name) ));
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
        seller.removeArtifact(aircraft);
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
}
