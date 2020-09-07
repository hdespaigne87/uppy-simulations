package com.uppy.simulations.driver_app.random_trips.dto;

import com.uppy.simulations.utils.FirebaseLocationDTO;
import lombok.Data;

@Data
public final class TripRequestDTO {

    private Integer userId;
    private Long startDate;
    private Double price;
    private String distance;
    private String duration;
    private FirebaseLocationDTO origin;
    private FirebaseLocationDTO destination;
    private String destinationAddress;
    private String originAddress;
}
