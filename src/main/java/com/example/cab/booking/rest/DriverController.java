package com.example.cab.booking.rest;

import com.example.cab.booking.entity.DriverStatus;
import com.example.cab.booking.entity.Location;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/driver")
public class DriverController {

    @PatchMapping("/{driverId}/location")
    public String updateDriverLocation(@RequestBody Location location){
        return "Location updated.";
    }

    @PatchMapping("/{driverId}/status")
    public String updateDriverLocation(@RequestBody DriverStatus status){
        return "Driver status updated.";
    }

    @PostMapping("/{rideId}/accept")
    public String acceptRide(){
        return "Ride accepted";
    }

    @PostMapping("/{rideId}/decline")
    public String declineRide(){
        return "Ride declined";
    }
}
