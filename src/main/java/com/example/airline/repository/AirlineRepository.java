package com.example.airline.repository;

import com.example.airline.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Integer> {
    @Query("SELECT balance FROM Airline airline WHERE airline.name=?1")
    long findBalanceByName(String name);

    @Query("SELECT airline FROM Airline airline WHERE airline.name=?1")
    Airline findByName(String name);
}
