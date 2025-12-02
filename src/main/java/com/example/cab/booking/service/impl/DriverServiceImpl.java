package com.example.cab.booking.service.impl;

import com.example.cab.booking.entity.*;
import com.example.cab.booking.repository.RideRepository;
import com.example.cab.booking.repository.UserRepository;
import com.example.cab.booking.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RideRepository rideRepository;

    @Override
    public ResponseEntity<String> updateDriverLocation(String driverId, Location location) {
        Optional<User> user = userRepository.findById(driverId);
        User driver = user.orElseThrow(() -> new RuntimeException("Driver not found."));
        if(!(driver instanceof Driver)){
            throw new RuntimeException("Driver not found.");
        }
        ((Driver) driver).setLocation(location);
        userRepository.save(driver);
        return ResponseEntity.ok("Updated driver location.");
    }

    @Override
    public ResponseEntity<String> updateDriverStatus(String driverId, DriverStatus driverStatus) {
        Optional<User> user = userRepository.findById(driverId);
        User driver = user.orElseThrow(() -> new RuntimeException("Driver not found."));
        if(!(driver instanceof Driver)){
            throw new RuntimeException("Driver not found.");
        }
        ((Driver) driver).setStatus(driverStatus);
        userRepository.save(driver);
        return ResponseEntity.ok("Updated driver status.");
    }

    @Override
    public ResponseEntity<String> updateRideStatus(String driverId, String rideId, RideStatus rideStatus) {
        Ride ride = rideRepository.findByDriverIdAndRideId(driverId, rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found."));
        ride.setStatus(rideStatus);
        rideRepository.save(ride);
        return ResponseEntity.ok("Updated ride status.");
    }

    @Override
    public ResponseEntity<Driver> findClosestAvailableDriver(String driverId, String rideId, Location pickup) {
        List<Driver> driverList = userRepository.findAllAvailableDrivers();
        Optional<Driver> closestDriver = driverList.stream()
                .sorted(Comparator.comparingDouble(driver ->
                        Location.findDistance(driver.getLocation(), pickup)))
                .findFirst();
        return ResponseEntity.ok(closestDriver.orElseThrow(() -> new RuntimeException("No driver found.")));
    }
}
