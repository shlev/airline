package com.example.airline.controller;

import com.example.airline.exceptions.DestinationNotFoundException;
import com.example.airline.model.Destination;
import com.example.airline.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        try {
            return ResponseEntity.ok(destinationService.getById(id));
        } catch (DestinationNotFoundException destinationNotFoundException) {
            return new ResponseEntity(new DestinationNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDestinationById(@PathVariable Long id) {
        try {
            destinationService.deleteById(id);
            return ResponseEntity.ok("Deleted successfully");
        } catch ( DestinationNotFoundException destinationNotFoundException) {
            return new ResponseEntity(new DestinationNotFoundException(id).getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateDestinationById(@PathVariable Long id, @RequestBody Destination newDestination) {
        try {
            Destination destination = destinationService.updateById(id, newDestination);
            return ResponseEntity.ok(destination);
        } catch (DestinationNotFoundException destinationNotFoundException) {
            return new ResponseEntity(destinationNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/distance")
    public ResponseEntity get(@RequestParam Long fromId, @RequestParam Long toId) {
        try {
            return ResponseEntity.ok(destinationService.getDistanceGeoCalc(fromId, toId));
        } catch (DestinationNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
