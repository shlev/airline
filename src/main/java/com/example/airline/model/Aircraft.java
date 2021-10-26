package com.example.airline.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private long price;
    private long maxDistance;
    private Date startedAt;

    @ManyToOne
    private Airline airline;

    public Aircraft() {}

    public Aircraft(String name, long price, long maxDistance) {
        this(name, price, maxDistance, new Date());
    }

    public Aircraft(String name, long price, long maxDistance, Date startAt) {
        this.name = name;
        this.price = price;
        this.maxDistance = maxDistance;
        this.startedAt = startAt;
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(long maxDistance) {
        this.maxDistance = maxDistance;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Aircraft))
            return false;
        Aircraft aircraft = (Aircraft) o;
        return getId() == aircraft.getId() && getPrice() == aircraft.getPrice() && getMaxDistance() == aircraft.getMaxDistance() && getName().equals(aircraft.getName()) && getStartedAt().equals(aircraft.getStartedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice(), getMaxDistance(), getStartedAt());
    }
}
