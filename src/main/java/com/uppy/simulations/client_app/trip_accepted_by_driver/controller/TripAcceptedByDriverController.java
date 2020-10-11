package com.uppy.simulations.client_app.trip_accepted_by_driver.controller;

import com.firebase.geofire.core.GeoHash;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.GeoPoint;
import com.google.cloud.firestore.WriteResult;
import com.uppy.simulations.client_app.trip_accepted_by_driver.dto.*;
import com.uppy.simulations.utils.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Random;

@Named
@ViewScoped
@Data
public class TripAcceptedByDriverController implements Serializable {

    @Autowired
    private Firestore firestore;

    private String customerId;
    private int driverId = 2;

    private TripAcceptedByDriverDTO buildDTO() {
        Random random = new Random();
        int status = random.nextInt(Integer.MAX_VALUE);
        String avatar = "https://cdn.icon-icons.com/icons2/1508/PNG/512/systemusers_104569.png";
        UserModelDTO driver = new UserModelDTO(driverId, "Juan", "Pérez",
                "Juan Pérez", "juanperez@gmail.com", "+5952323223",
                status,avatar);

        CustomLatLngDTO origin = new CustomLatLngDTO(-25.293115, -57.622006);
        CustomLatLngDTO destination = new CustomLatLngDTO(-25.294293, -57.611450);

        CarInfoDTO carInfo = new CarInfoDTO("Santa Fe", "Hyundai", "GHE223");

        int tripId = random.nextInt(Integer.MAX_VALUE);
        int price = random.nextInt();
        return new TripAcceptedByDriverDTO(tripId, driver, price, "10 km",
                "20 min", LocalDate.now().toEpochDay(), origin, destination, "Av. General Santos c/ Eligio Ayala",
                "Feliz de Azara c/ Perú", carInfo);
    }

    private void updateDriverPositions() {
        double latitude = -25.2858633;
        double longitude = -57.629655;
        for (int i = 0; i < 10; i++) {
            RandomLocationGenerator.Location location = RandomLocationGenerator.getRandomLocation(latitude, longitude, 1);
            GeoPoint originGeoPoint = new GeoPoint(location.getLatitude(), location.getLongitude());
            String originGeoHash = new GeoHash(location.getLatitude(), location.getLongitude()).getGeoHashString();
            DriverPositionDTO driverPositionDTO = new DriverPositionDTO(new FirebaseLocationDTO(originGeoHash, originGeoPoint), false);
            WriteResult writeResult = null;
            try {
                writeResult = this.firestore.document("drivers/" + driverId).set(driverPositionDTO).get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println(String.format("Driver location updated to latitude <%s> and longitude <%s> at <%s>: ", location.getLatitude(), location.getLongitude(), writeResult.getUpdateTime()));
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void sendNotificationToPusher() {
        PusherUtil.sendMessage("user-" + customerId, "driver-found", buildDTO());
        JsfUtil.addSuccessfulOperationMessage();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        updateDriverPositions();
    }
}
