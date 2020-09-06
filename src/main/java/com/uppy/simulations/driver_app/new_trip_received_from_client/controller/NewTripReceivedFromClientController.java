package com.uppy.simulations.driver_app.new_trip_received_from_client.controller;

import com.uppy.simulations.driver_app.new_trip_received_from_client.dto.FibonacciCalculationMethod;
import com.uppy.simulations.driver_app.new_trip_received_from_client.dto.FibonacciResult;
import lombok.Data;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
@Data
public class NewTripReceivedFromClientController implements Serializable {

    private Integer number;
    private FibonacciResult result;

    public void calculate(FibonacciCalculationMethod method) throws Exception {
    }
}
