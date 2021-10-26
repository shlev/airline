package com.example.airline.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private long balance;

    @OneToOne
    private Destination homeBase;

    @OneToMany(mappedBy = "airline", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Aircraft> aircrafts = new HashSet<>();

    @ManyToMany(mappedBy = "airlines", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Destination> destinations = new HashSet<>();

    public Airline() {}

    public Airline(String name, long balance, Destination homeBase) {
        this.name = name;
        this.balance = balance;
        this.homeBase = homeBase;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long initialBudget) {
        this.balance = initialBudget;
    }

    public Destination getHomeBase() {
        return homeBase;
    }

    public void setHomeBase(Destination homeBase) {
        this.homeBase = homeBase;
    }

    public void setAircrafts(Set<Aircraft> aircrafts) {
        this.aircrafts = aircrafts;
    }

    public Set<Destination> getDestinations() {
        Set<Destination> r = new HashSet<>(destinations);
        r.add(homeBase);
        return r;
    }

    public void setDestinations(Set<Destination> destinations) {
        this.destinations = destinations;
    }

    public void addAircraft(Aircraft aircraft) {
        aircrafts.add(aircraft);
        aircraft.setAirline(this);
    }

    public void addDestination(Destination destination) {
        destinations.add(destination);
        destination.addAirline(this);
    }

    public void removeAircraft(Aircraft aircraft) {
        aircrafts.remove(aircraft);
        aircraft.setAirline(null);
    }

    public Set<Aircraft> getAircrafts() {
        return this.aircrafts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Airline))
            return false;
        Airline airline = (Airline) o;
        return getId() == airline.getId() && getBalance() == airline.getBalance() && getName().equals(airline.getName()) && getHomeBase().equals(airline.getHomeBase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getBalance(), getHomeBase(), getAircrafts());
    }
}
