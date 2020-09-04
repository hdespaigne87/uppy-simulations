package com.uppy.simulations.driver_app.new_trip_received_from_client.service;

import com.uppy.simulations.driver_app.new_trip_received_from_client.dto.FibonacciCalculationMethod;
import com.uppy.simulations.driver_app.new_trip_received_from_client.dto.FibonacciResult;
import com.uppy.simulations.api_config.ApiRestConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class FibonacciServiceImpl implements FibonacciService {

    @Override
    public FibonacciResult calculate(Integer number, FibonacciCalculationMethod method) throws Exception {
        if (number == null)
            throw new Exception("Null number is not allowed");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ApiRestConfig.API_URL_BASE)
                .path("fibonacci")
                .queryParam("number", number)
                .queryParam("method", method);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FibonacciResult> resultEntity = restTemplate.getForEntity(builder.toUriString(), FibonacciResult.class);
        return resultEntity.getBody();
    }
}
