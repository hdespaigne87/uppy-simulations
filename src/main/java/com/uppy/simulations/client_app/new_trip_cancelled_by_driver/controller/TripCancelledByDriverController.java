package com.uppy.simulations.client_app.new_trip_cancelled_by_driver.controller;

import com.uppy.simulations.client_app.new_trip_cancelled_by_driver.dto.TripCancelledByDriverDTO;
import com.uppy.simulations.utils.JsfUtil;
import com.uppy.simulations.utils.JsonUtil;
import com.uppy.simulations.utils.PusherUtil;
import lombok.Data;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
@Data
public class TripCancelledByDriverController implements Serializable {

    private String customerId;

    public void sendNotificationToPusher() {
        PusherUtil.sendMessage("user-" + customerId, "trip-canceled", JsonUtil.toJson(new TripCancelledByDriverDTO()));
        JsfUtil.addSuccessfulOperationMessage();
    }
}
