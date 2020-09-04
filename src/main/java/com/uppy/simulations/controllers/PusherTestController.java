package com.uppy.simulations.controllers;

import com.uppy.simulations.utils.PusherUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class PusherTestController {

    @GetMapping("test")
    public ResponseEntity<?> hds() {
        PusherUtil.sendMessage("test", "event_test", "Hello World");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
