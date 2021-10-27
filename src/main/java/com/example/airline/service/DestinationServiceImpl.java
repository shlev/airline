package com.example.airline.service;

import com.example.airline.dto.DistanceDTO;
import com.example.airline.exceptions.DestinationNotFoundException;
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

    @Override
    public Destination create(Destination destination) {
        return destinationRepository.save(destination);
    }

    @Override
    public List<Destination> getAllDestination() {
        return destinationRepository.findAll();
    }

    @Override
    public Destination getById(Long id) throws DestinationNotFoundException{
        Optional<Destination> optionalDestination = destinationRepository.findById(id);
        if (optionalDestination.isEmpty()) throw new DestinationNotFoundException(id);
        return optionalDestination.get();
     }

    @Override
    public void deleteById(Long id) throws DestinationNotFoundException{
        Optional<Destination> optionalDestination = destinationRepository.findById(id);
        if (optionalDestination.isEmpty()) throw new DestinationNotFoundException(id);
        destinationRepository.deleteById(id);
    }

    @Override
    public Destination updateById(Long id, Destination newDestination) throws DestinationNotFoundException {
        Optional<Destination> optionalDestination = destinationRepository.findById(id);
        if (optionalDestination.isEmpty()) throw new DestinationNotFoundException(id);
        Destination destination = optionalDestination.get();
        destination.setAirlines(newDestination.getAirlines());
        destination.setCity(newDestination.getCity());
        destination.setCountry(newDestination.getCountry());
        destination.setLatitude(newDestination.getLatitude());
        destination.setLongitude(newDestination.getLongitude());
        destination.setHomeBaseAirline(newDestination.getHomeBaseAirline());
        destination.setName(newDestination.getName());

        return destinationRepository.save(destination);
    }

    @Override
    public double getDistanceGeoCalc(Destination a, Destination b) {
        Coordinate lat = Coordinate.fromDegrees(a.getLatitude());
        Coordinate lng = Coordinate.fromDegrees(a.getLongitude());
        Point pointA = Point.at(lat, lng);

        lat = Coordinate.fromDegrees(b.getLatitude());
        lng = Coordinate.fromDegrees(b.getLongitude());
        Point pointB = Point.at(lat, lng);

        double distance = EarthCalc.gcd.distance(pointB, pointA); //in meters
        return distance/1000;
    }

    @Override
    public double getDistanceGeoCalc(Long fromId, Long toId) throws DestinationNotFoundException{
        Destination fromDestination = getById(fromId);
        Destination toDestination = getById(toId);
        return getDistanceGeoCalc(fromDestination, toDestination);
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
