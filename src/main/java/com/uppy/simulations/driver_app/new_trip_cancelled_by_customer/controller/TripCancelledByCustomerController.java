package com.uppy.simulations.driver_app.new_trip_cancelled_by_customer.controller;

import com.uppy.simulations.driver_app.new_trip_cancelled_by_customer.dto.TripCancelledByCustomerDTO;
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
public class TripCancelledByCustomerController implements Serializable {

    private String driverId;

    public void sendNotificationToPusher() {
        PusherUtil.sendMessage("driver-" + driverId, "trip-canceled", JsonUtil.toJson(new TripCancelledByCustomerDTO()));
        JsfUtil.addSuccessfulOperationMessage();
    }
}
