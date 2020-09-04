package com.uppy.simulations.client_app.driver_not_found.controller;

import com.uppy.simulations.client_app.driver_not_found.dto.DriverNotFoundDTO;
import com.uppy.simulations.utils.JsonUtil;
import com.uppy.simulations.utils.PusherUtil;
import lombok.Data;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
@Data
public class DriverNotFoundController implements Serializable {

    private String customerId;

    public void sendNotificationToPusher() {
        PusherUtil.sendMessage("user-" + customerId, "driver-not-found", JsonUtil.toJson(new DriverNotFoundDTO()));
    }
}
