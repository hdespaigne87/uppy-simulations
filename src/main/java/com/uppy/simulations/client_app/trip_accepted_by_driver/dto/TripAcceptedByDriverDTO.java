package com.uppy.simulations.client_app.trip_accepted_by_driver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TripAcceptedByDriverDTO {

    private int tripId;
    private UserModelDTO driver;
    private int price;
    private String distance;
    private String duration;
    private Long startDate;
    private CustomLatLngDTO origin;
    private CustomLatLngDTO destination;
    private String destinationAddress;
    private String originAddress;
    private CarInfoDTO carInfo;
}
