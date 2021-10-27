package com.example.airline.controller;

import com.example.airline.exceptions.DestinationNotFoundException;
import com.example.airline.model.Destination;
import com.example.airline.service.DestinationService;
import com.example.airline.service.DestinationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/destination")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @PostMapping
    public ResponseEntity createDestination(@RequestBody Destination destination) {
        Destination newDestination = destinationService.create(destination);
        return ResponseEntity.ok(newDestination);
    }

    @GetMapping
    public ResponseEntity getAllDestinations() {
        List<Destination> allDestination = destinationService.getAllDestination();
        return ResponseEntity.ok(allDestination);
    }

    @GetMapping("/{id}")
    public ResponseEntity getDestinationById(@PathVariable Long id) {
        Optional<Destination> opt = destinationService.getById(id);
        if ( opt.isEmpty()) {
            return new ResponseEntity(new DestinationNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(opt.get());
    }

}
