package com.example.cab.booking.rest;

import com.example.cab.booking.entity.DriverStatus;
import com.example.cab.booking.entity.Location;
import com.example.cab.booking.entity.RideStatus;
import com.example.cab.booking.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PatchMapping("/{driverId}/location")
    public ResponseEntity<String> updateDriverLocation(@PathVariable String driverId, @RequestBody Location location){
        return driverService.updateDriverLocation(driverId, location);
    }

    @PatchMapping("/{driverId}/status")
    public ResponseEntity<String> updateDriverStatus(@PathVariable String driverId,@RequestBody DriverStatus status){
        return driverService.updateDriverStatus(driverId, status);
    }

    @PostMapping("/{driverId}/ride/{rideId}/accept")
    public ResponseEntity<String> acceptRide(@PathVariable String driverId, @PathVariable String rideId){
        return driverService.updateRideStatus(driverId, rideId, RideStatus.DRIVER_ACCEPTED);
    }

    @PostMapping("/{driverId}/ride/{rideId}/decline")
    public ResponseEntity<String> declineRide(@PathVariable String driverId, @PathVariable String rideId){
        return driverService.updateRideStatus(driverId, rideId, RideStatus.DRIVER_DECLINED);
    }
}
