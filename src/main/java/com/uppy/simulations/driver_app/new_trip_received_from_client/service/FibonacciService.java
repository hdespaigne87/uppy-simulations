package com.uppy.simulations.driver_app.new_trip_received_from_client.service;

import com.uppy.simulations.driver_app.new_trip_received_from_client.dto.FibonacciCalculationMethod;
import com.uppy.simulations.driver_app.new_trip_received_from_client.dto.FibonacciResult;

public interface FibonacciService {

    FibonacciResult calculate(Integer number, FibonacciCalculationMethod method) throws Exception;
}
