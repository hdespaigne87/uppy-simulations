package com.uppy.simulations.driver_app.new_trip_received_from_client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class UserModelDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String fullname;
    private String email;
    private String cellphone;
    private int status;
}
