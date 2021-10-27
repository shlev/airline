package com.example.airline.service;

import com.example.airline.exceptions.DestinationNotFoundException;
import com.example.airline.model.Destination;

import java.util.List;

public interface DestinationService {

    Destination create(Destination destination);

    List<Destination> getAllDestination();

    Destination getById(Long id);

    void deleteById(Long id) throws DestinationNotFoundException;

    Destination updateById(Long id, Destination newDestination) throws DestinationNotFoundException;

    double getDistanceGeoCalc(Destination a, Destination b);

    double getDistanceGeoCalc(Long fromId, Long toId) throws DestinationNotFoundException;
}
