package com.uppy.simulations.client_app.trip_accepted_by_driver.dto;

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
    private String avatar;
}
