package com.example.airline.model;




import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String country;
    private String city;
    private double latitude;
    private double longitude;

    @OneToOne
    private Airline homeBaseAirline;

    @ManyToMany
    private Set<Airline> airlines = new HashSet<>();

    public Destination() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Airline getHomeBaseAirline() {
        return homeBaseAirline;
    }

    public void setHomeBaseAirline(Airline homeBaseAirline) {
        this.homeBaseAirline = homeBaseAirline;
    }

    public Set<Airline> getAirlines() {
        return airlines;
    }

    public void setAirlines(Set<Airline> airlines) {
        this.airlines = airlines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Destination))
            return false;
        Destination that = (Destination) o;
        return getId() == that.getId() && Double.compare(that.getLatitude(), getLatitude()) == 0 && Double.compare(that.getLongitude(), getLongitude()) == 0 && Objects.equals(getCountry(), that.getCountry()) && Objects.equals(getCity(), that.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCountry(), getCity(), getLatitude(), getLongitude());
    }
}
