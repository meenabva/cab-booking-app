package com.example.cab.booking.service.impl;

import com.example.cab.booking.entity.*;
import com.example.cab.booking.repository.UserRepository;
import com.example.cab.booking.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class DriverServiceImpl implements DriverService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String updateDriverLocation(String driverId, Location location) {
        Optional<User> user = userRepository.findById(driverId);
        User driver = user.orElseThrow(() -> new RuntimeException("Driver not found."));
        if(!(driver instanceof Driver)){
            throw new RuntimeException("Driver not found.");
        }
        ((Driver) driver).setLocation(location);
        userRepository.save(driver);
        return "Updated driver location.";
    }

    @Override
    public String updateDriverStatus(String driverId, DriverStatus driverStatus) {
        Optional<User> user = userRepository.findById(driverId);
        User driver = user.orElseThrow(() -> new RuntimeException("Driver not found."));
        if(!(driver instanceof Driver)){
            throw new RuntimeException("Driver not found.");
        }
        ((Driver) driver).setStatus(driverStatus);
        userRepository.save(driver);
        return "Updated driver status.";
    }

    @Override
    public String updateRideStatus(String driverId, String rideId, RideStatus rideStatus) {
        return "";
    }

    @Override
    public Driver findClosestAvailableDriver(String driverId, String rideId, Location pickup) {
        List<Driver> driverList = userRepository.findAllAvailableDrivers();
        Optional<Driver> closestDriver = driverList.stream()
                .sorted(Comparator.comparingDouble(driver ->
                        Location.findDistance(driver.getLocation(), pickup)))
                .findFirst();
        return closestDriver.orElseThrow(() -> new RuntimeException("No driver found."));
    }
}
