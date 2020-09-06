package com.uppy.simulations.client_app.trip_accepted_by_driver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class DriverPositionDTO {

    private FirebaseLocation position;
    private boolean reserved;
}
