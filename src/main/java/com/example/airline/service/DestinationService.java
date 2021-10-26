package com.example.airline.service;

import com.example.airline.model.Destination;
import com.example.airline.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationService {

    @Autowired
    DestinationRepository destinationRepository;
    public List<Destination> getAll() {
        return destinationRepository.findAll();
    }

    public Optional<Destination> getByID(long id) {
        return destinationRepository.findById(id);
    }

    public void getDistance(Destination destination1, Destination destination2) {
    }
}
