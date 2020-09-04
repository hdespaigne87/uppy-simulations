package com.uppy.simulations.model;

import lombok.Data;

@Data
public final class TripRequest {

    private Integer userId;
    private Long startDate;
    private Double price;
    private String distance;
    private String duration;
    private Location origin;
    private Location destination;
    private String destinationAddress;
    private String originAddress;
}
