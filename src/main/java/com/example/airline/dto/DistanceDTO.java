package com.example.airline.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DistanceDTO {

    private String airlineName;
    private String destinationName;
    private double distance;
}
