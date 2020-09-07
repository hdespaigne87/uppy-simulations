package com.uppy.simulations.utils;

import com.google.cloud.firestore.GeoPoint;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class FirebaseLocationDTO {

    private String geohash;
    private GeoPoint geopoint;
}
