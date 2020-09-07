package com.uppy.simulations.driver_app.new_trip_received_from_client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class CarInfoDTO {

    private String model;
    private String brand;
    private String plate;
}
