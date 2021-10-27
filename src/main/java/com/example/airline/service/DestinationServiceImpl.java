package com.example.airline.service;

import com.example.airline.dto.DistanceDTO;
import com.example.airline.model.Airline;
import com.example.airline.model.Destination;
import com.example.airline.repository.DestinationRepository;
import com.grum.geocalc.Coordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DestinationServiceImpl implements DestinationService{

    @Autowired
    DestinationRepository destinationRepository;

    public List<Destination> getAll() {
        return destinationRepository.findAll();
    }

    public Optional<Destination> getByID(long id) {
        return destinationRepository.findById(id);
    }

    @Override
    public Destination create(Destination destination) {
        return destinationRepository.save(destination);
    }

    @Override
    public List<Destination> getAllDestination() {
        return destinationRepository.findAll();
    }

    @Override
    public Optional<Destination> getById(Long id) {
        return destinationRepository.findById(id);
    }

    public double getDistanceGeoCalc(Destination a, Destination b) {
        Coordinate lat = Coordinate.fromDegrees(a.getLatitude());
        Coordinate lng = Coordinate.fromDegrees(a.getLongitude());
        Point kew = Point.at(lat, lng);

        lat = Coordinate.fromDegrees(b.getLatitude());
        lng = Coordinate.fromDegrees(b.getLongitude());
        Point richmond = Point.at(lat, lng);

        double distance = EarthCalc.gcd.distance(richmond, kew); //in meters
        return distance/1000;
    }

    public Set<DistanceDTO> getAllDestinationsDistance(Airline airline) {
        List<Destination> allDestinations = getAll();
        return allDestinations.stream().map(destination -> getDistance(destination, airline)).collect(Collectors.toSet());
    }

    private DistanceDTO getDistance(Destination destination, Airline airline) {
        double distanceGeoCalc = getDistanceGeoCalc(airline.getHomeBase(), destination);
        return new DistanceDTO(airline.getName(), destination.getName(), distanceGeoCalc);
    }
}
