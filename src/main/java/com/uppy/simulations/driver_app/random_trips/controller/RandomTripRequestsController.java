package com.uppy.simulations.driver_app.random_trips.controller;

import com.firebase.geofire.core.GeoHash;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.GeoPoint;
import com.google.cloud.firestore.WriteResult;
import com.uppy.simulations.driver_app.random_trips.dto.TripRequestDTO;
import com.uppy.simulations.utils.FirebaseLocationDTO;
import com.uppy.simulations.utils.RandomLocationGenerator;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Named
@ViewScoped
@Data
public class RandomTripRequestsController implements Serializable {

    @Autowired
    private Firestore firestore;

    private double latitude = 25.2968485;
    private double longitude = -57.6331085;
    private int radiusKm = 3;
    private int quantityOfTrips = 4;
    private int waitingSecondsToDeleteGeneratedTrips = 60;

    private TripRequestDTO generateTrip(double latitude, double longitude) throws Exception {
        Random random = new Random();
        int clientId = random.nextInt(Integer.MAX_VALUE);
        while (clientId == 1) {
            clientId = random.nextInt(Integer.MAX_VALUE);
        }
        TripRequestDTO tripRequestDTO = new TripRequestDTO();
        tripRequestDTO.setDistance("18,5 km");
        tripRequestDTO.setDuration("27 min");
        tripRequestDTO.setUserId(clientId);
        tripRequestDTO.setPrice(12345d);
        tripRequestDTO.setStartDate(System.currentTimeMillis());
        tripRequestDTO.setOriginAddress("Origin Address");
        tripRequestDTO.setDestinationAddress("Destination Address");

        RandomLocationGenerator.Location location = RandomLocationGenerator.getRandomLocation(latitude, longitude, radiusKm);
        GeoPoint originGeoPoint = new GeoPoint(location.getLatitude(), location.getLongitude());
        String originGeoHash = new GeoHash(location.getLatitude(), location.getLongitude()).getGeoHashString();
        location = RandomLocationGenerator.getRandomLocation(latitude, longitude, radiusKm);
        GeoPoint destinationGeoPoint = new GeoPoint(location.getLatitude(), location.getLongitude());
        String destinationGeoHash = new GeoHash(location.getLatitude(), location.getLongitude()).getGeoHashString();

        tripRequestDTO.setOrigin(new FirebaseLocationDTO(originGeoHash, originGeoPoint));
        tripRequestDTO.setDestination(new FirebaseLocationDTO(destinationGeoHash, destinationGeoPoint));

        WriteResult writeResult = this.firestore.document("trips_availables/" + tripRequestDTO.getUserId()).set(tripRequestDTO).get();

        System.out.println("Update time: " + writeResult.getUpdateTime());
        return tripRequestDTO;
    }

    private void deleteTrip(int userId) throws Exception {
        WriteResult writeResult = this.firestore.document("trips_availables/" + userId).delete().get();
        System.out.println("Delete time: " + writeResult.getUpdateTime());
    }

    public void createRandomTripsInFirebase() {
        //25.2968485,-57.6331085
        try {
            List<TripRequestDTO> trips = new ArrayList<>();
            for (int i = 0; i < quantityOfTrips; i++) {
                trips.add(generateTrip(latitude, longitude));
            }
            Thread.sleep(waitingSecondsToDeleteGeneratedTrips * 1000);
            for (TripRequestDTO tripRequestDTO : trips) {
                deleteTrip(tripRequestDTO.getUserId());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
