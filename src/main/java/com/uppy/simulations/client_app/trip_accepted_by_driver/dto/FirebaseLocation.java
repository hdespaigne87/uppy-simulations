package com.uppy.simulations.client_app.trip_accepted_by_driver.dto;

import com.google.cloud.firestore.GeoPoint;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class FirebaseLocation {

    private String geohash;
    private GeoPoint geopoint;
}
