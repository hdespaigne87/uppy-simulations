package com.uppy.simulations.client_app.trip_accepted_by_driver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HighestRepeatingWordResult {

    private String word;
    private Integer timesRepeated;
}
