package com.example.cab.booking.service;

import com.example.cab.booking.entity.*;
import org.springframework.http.ResponseEntity;

public interface DriverService {

    public ResponseEntity<String> updateDriverLocation(String driverId, Location location);

    public ResponseEntity<String> updateDriverStatus(String driverId, DriverStatus driverStatus);

    public ResponseEntity<String> updateRideStatus(String driverId, String rideId, RideStatus rideStatus);

    public Driver findClosestAvailableDriver(Location pickup);

    void assignClosestDriver(Ride ride);
}
