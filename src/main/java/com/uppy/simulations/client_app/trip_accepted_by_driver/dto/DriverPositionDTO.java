package com.uppy.simulations.client_app.trip_accepted_by_driver.dto;

import com.uppy.simulations.utils.FirebaseLocationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class DriverPositionDTO {

    private FirebaseLocationDTO position;
    private boolean reserved;
}
