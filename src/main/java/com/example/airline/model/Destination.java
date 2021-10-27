package com.example.airline.model;




import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@NoArgsConstructor
@Data
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String country;
    private String city;
    private double latitude;
    private double longitude;

    @OneToOne
    private Airline homeBaseAirline;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Airline> airlines = new HashSet<>();

    public void addAirline(Airline airline) {
        this.airlines.add(airline);
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
