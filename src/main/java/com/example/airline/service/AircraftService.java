package com.example.airline.service;

import com.example.airline.model.Aircraft;
import com.example.airline.repository.AircraftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class AircraftService {

    @Autowired
    private AircraftRepository aircraftRepository;

    public Aircraft save(Aircraft aircraft) {
        return aircraftRepository.save(aircraft);
    }

    public List<Aircraft> getAll() {
        return aircraftRepository.findAll();
    }


    public Aircraft getByName(String name) {
        return aircraftRepository.findByName(name);
    }

    public long getSellPrice(Aircraft aircraft, Date sellDate) {
        long diff = sellDate.getTime() - aircraft.getStartedAt().getTime();
        long months = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)/30;
        return (long)(aircraft.getPrice()*(1 - months*0.02));
    }

    public long getSellPrice(Aircraft aircraft) {
        return getSellPrice(aircraft, new Date());
    }

    public long getSellPrice(String name) {
        return getSellPrice(getByName(name));
    }
}
