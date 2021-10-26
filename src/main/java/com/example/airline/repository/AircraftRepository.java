package com.example.airline.repository;

import com.example.airline.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
    @Query("SELECT aircraft FROM Aircraft aircraft WHERE aircraft.name=?1")
    Aircraft findByName(String name);
}
