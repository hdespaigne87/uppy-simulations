package com.uppy.simulations.driver_app.new_trip_received_from_client.controller;

import com.uppy.simulations.driver_app.new_trip_received_from_client.dto.CarInfoDTO;
import com.uppy.simulations.driver_app.new_trip_received_from_client.dto.CustomLatLngDTO;
import com.uppy.simulations.driver_app.new_trip_received_from_client.dto.TripReceivedFromClientDTO;
import com.uppy.simulations.driver_app.new_trip_received_from_client.dto.UserModelDTO;
import com.uppy.simulations.utils.JsfUtil;
import com.uppy.simulations.utils.PusherUtil;
import lombok.Data;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Random;

@Named
@ViewScoped
@Data
public class NewTripReceivedFromClientController implements Serializable {

    private String driverId;
    private int customerId = 1;

    private TripReceivedFromClientDTO buildDTO() {
        Random random = new Random();
        CustomLatLngDTO origin = new CustomLatLngDTO(-25.293115, -57.622006);
        CustomLatLngDTO destination = new CustomLatLngDTO(-25.294293, -57.611450);

        CarInfoDTO carInfo = new CarInfoDTO("Santa Fe", "Hyundai", "GHE223");
        int status = random.nextInt(Integer.MAX_VALUE);
        UserModelDTO client = new UserModelDTO(customerId, "Juan", "Pérez",
                "Juan Pérez", "juanperez@gmail.com", "+5952323223",
                status);

        int tripId = random.nextInt(Integer.MAX_VALUE);
        double price = random.nextDouble();
        return new TripReceivedFromClientDTO(tripId, price, "10 km",
                "20 min", LocalDate.now().toEpochDay(), origin, destination, "Av. General Santos c/ Eligio Ayala",
                "Feliz de Azara c/ Perú", carInfo, client, 1, "Efectivo");
    }

    public void sendNotificationToPusher() {
        PusherUtil.sendMessage("driver-" + driverId, "new-trip-request", buildDTO());
        JsfUtil.addSuccessfulOperationMessage();
    }
}
