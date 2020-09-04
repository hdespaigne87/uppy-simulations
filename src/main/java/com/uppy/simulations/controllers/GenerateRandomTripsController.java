package com.uppy.simulations.controllers;

import com.uppy.simulations.model.Location;
import com.uppy.simulations.model.TripRequest;
import com.uppy.simulations.utils.RandomLocationGenerator;
import com.firebase.geofire.core.GeoHash;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.GeoPoint;
import com.google.cloud.firestore.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/firestore")
public final class GenerateRandomTripsController {

    @Autowired
    private Firestore firestore;

    private TripRequest generateTrip(double latitude, double longitude) throws Exception {
        Random random = new Random();
        int clientId = random.nextInt(Integer.MAX_VALUE);
        while (clientId == 1) {
            clientId = random.nextInt(Integer.MAX_VALUE);
        }
        TripRequest tripRequest = new TripRequest();
        tripRequest.setDistance("18,5 km");
        tripRequest.setDuration("27 min");
        tripRequest.setUserId(clientId);
        tripRequest.setPrice(12345d);
        tripRequest.setStartDate(System.currentTimeMillis());
        tripRequest.setOriginAddress("Origin Address");
        tripRequest.setDestinationAddress("Destination Address");

        RandomLocationGenerator.Location location = RandomLocationGenerator.getRandomLocation(latitude, longitude,3);
        GeoPoint originGeoPoint = new GeoPoint(location.getLatitude(), location.getLongitude());
        String originGeoHash = new GeoHash(location.getLatitude(), location.getLongitude()).getGeoHashString();
        location = RandomLocationGenerator.getRandomLocation(latitude, longitude,3);
        GeoPoint destinationGeoPoint = new GeoPoint(location.getLatitude(), location.getLongitude());
        String destinationGeoHash = new GeoHash(location.getLatitude(), location.getLongitude()).getGeoHashString();

        tripRequest.setOrigin(new Location(originGeoHash, originGeoPoint));
        tripRequest.setDestination(new Location(destinationGeoHash, destinationGeoPoint));

        WriteResult writeResult = this.firestore.document("trips_availables/" + tripRequest.getUserId()).set(tripRequest).get();

        System.out.println("Update time: " + writeResult.getUpdateTime());
        return tripRequest;
    }

    private void deleteTrip(int userId) throws Exception {
        WriteResult writeResult = this.firestore.document("trips_availables/" + userId).delete().get();
        System.out.println("Delete time: " + writeResult.getUpdateTime());
    }

    @PostMapping("/generate_random_trips")
    public void generate_random_trips(@RequestParam("latitude") double latitude,
                                                   @RequestParam("longitude") double longitude) throws Exception {
        //25.2968485,-57.6331085
        while (true) {
            TripRequest trip1 = generateTrip(latitude, longitude);
            TripRequest trip2 = generateTrip(latitude, longitude);
            TripRequest trip3 = generateTrip(latitude, longitude);
            TripRequest trip4 = generateTrip(latitude, longitude);
            Thread.sleep(900000);
            deleteTrip(trip1.getUserId());
            deleteTrip(trip2.getUserId());
            deleteTrip(trip3.getUserId());
            deleteTrip(trip4.getUserId());
        }
    }
}
