package com.example.airline.service;

import com.example.airline.model.Destination;

import java.util.List;
import java.util.Optional;

public interface DestinationService {

    Destination create(Destination destination);

    List<Destination> getAllDestination();

    Optional<Destination> getById(Long id);
}
