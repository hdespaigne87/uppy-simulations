package com.uppy.simulations.client_app.trip_accepted_by_driver.controller;

import com.uppy.simulations.client_app.trip_accepted_by_driver.dto.CustomLatLngDTO;
import com.uppy.simulations.client_app.trip_accepted_by_driver.dto.TripAcceptedByDriverDTO;
import com.uppy.simulations.client_app.trip_accepted_by_driver.dto.UserModelDTO;
import com.uppy.simulations.utils.JsfUtil;
import com.uppy.simulations.utils.JsonUtil;
import com.uppy.simulations.utils.PusherUtil;
import lombok.Data;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Named
@ViewScoped
@Data
public class TripAcceptedByDriverController implements Serializable {

    private String customerId;

    private TripAcceptedByDriverDTO buildDTO() {
        Random random = new Random();
        int driverId = random.nextInt(Integer.MAX_VALUE);
        int status = random.nextInt(Integer.MAX_VALUE);
        UserModelDTO driver = new UserModelDTO(driverId, "Juan", "Pérez",
                "Juan Pérez", "juanperez@gmail.com", "+5952323223",
                status);

        CustomLatLngDTO origin = new CustomLatLngDTO(-25.293115, -57.622006);
        CustomLatLngDTO destination = new CustomLatLngDTO(-25.294293, -57.611450);

        int tripId = random.nextInt(Integer.MAX_VALUE);
        double price = random.nextDouble();
        return new TripAcceptedByDriverDTO(tripId, driver, price, "10 km",
                "20 min", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), origin, destination, "Av. General Santos c/ Eligio Ayala",
                "Feliz de Azara c/ Perú");
    }

    public void sendNotificationToPusher() {
        PusherUtil.sendMessage("user-" + customerId, "driver-found", JsonUtil.toJson(buildDTO()));
        JsfUtil.addSuccessfulOperationMessage();
    }
}
