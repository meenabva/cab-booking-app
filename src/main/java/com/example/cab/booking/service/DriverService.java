package com.example.cab.booking.service;

import com.example.cab.booking.entity.Driver;
import com.example.cab.booking.entity.DriverStatus;
import com.example.cab.booking.entity.Location;
import com.example.cab.booking.entity.RideStatus;
import org.springframework.http.ResponseEntity;

public interface DriverService {

    public ResponseEntity<String> updateDriverLocation(String driverId, Location location);

    public ResponseEntity<String> updateDriverStatus(String driverId, DriverStatus driverStatus);

    public ResponseEntity<String> updateRideStatus(String driverId, String rideId, RideStatus rideStatus);

    public ResponseEntity<Driver> findClosestAvailableDriver(String driverId, String rideId, Location pickup);
}
