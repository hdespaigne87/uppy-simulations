package com.uppy.simulations.client_app.trip_accepted_by_driver.service;

import com.uppy.simulations.client_app.trip_accepted_by_driver.dto.HighestRepeatingWordResult;

import java.io.File;

public interface HighestRepeatingWordService {

    HighestRepeatingWordResult findHighestRepeatingWord(File fileObj);
}
