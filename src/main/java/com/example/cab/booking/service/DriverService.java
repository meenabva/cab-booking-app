package com.example.cab.booking.service;

import com.example.cab.booking.entity.Driver;
import com.example.cab.booking.entity.DriverStatus;
import com.example.cab.booking.entity.Location;
import com.example.cab.booking.entity.RideStatus;

public interface DriverService {

    public String updateDriverLocation(String driverId, Location location);

    public String updateDriverStatus(String driverId, DriverStatus driverStatus);

    public String updateRideStatus(String driverId, String rideId, RideStatus rideStatus);

    public Driver findClosestAvailableDriver(String driverId, String rideId, Location pickup);
}
