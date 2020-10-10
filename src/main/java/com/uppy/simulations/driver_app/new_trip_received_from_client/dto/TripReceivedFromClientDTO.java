package com.uppy.simulations.driver_app.new_trip_received_from_client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TripReceivedFromClientDTO {

    private int tripId;
    private double price;
    private String distance;
    private String duration;
    private Long startDate;
    private CustomLatLngDTO origin;
    private CustomLatLngDTO destination;
    private String destinationAddress;
    private String originAddress;
    private UserModelDTO client;
    private int paymentType;
    private String paymentName;
    private String distanceToClient;
    private String timeToClient;
}
