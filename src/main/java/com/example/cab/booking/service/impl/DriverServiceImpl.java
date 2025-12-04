package com.example.cab.booking.service.impl;

import com.example.cab.booking.entity.*;
import com.example.cab.booking.repository.RideRepository;
import com.example.cab.booking.repository.UserRepository;
import com.example.cab.booking.service.DriverService;
import com.example.cab.booking.strategy.PriceCalculationStrategy;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
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

    @Autowired
    private PriceCalculationStrategy priceCalculationStrategy;

    @Override
    @Transactional
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
    @Transactional
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
        saveRideStatus(driverId, rideId, rideStatus);
        return ResponseEntity.ok("Updated ride status.");
    }

    @Transactional
    private Ride saveRideStatus(String driverId, String rideId, RideStatus rideStatus) {
        Ride ride = rideRepository.findByDriverIdAndRideId(driverId, rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found."));
        ride.setStatus(rideStatus);
        ride = rideRepository.save(ride);
        return ride;
    }

    @Override
    public Driver findClosestAvailableDriver(Location pickup) {
        List<Driver> driverList = userRepository.findAllAvailableDrivers();
        Optional<Driver> closestDriver = driverList.stream()
                .filter(d -> d != null && d.getLocation() != null)
                                            .min(Comparator.comparingDouble(driver ->
                                                Location.findDistance(driver.getLocation(), pickup)));
        return closestDriver.orElseThrow(() -> new RuntimeException("No driver found."));
    }


    @Override
    @Transactional
    @Async
    public void assignClosestDriver(Ride ride) {
        Driver closestDriver = findClosestAvailableDriver(ride.getPickUp());
        ride.setDriver(closestDriver);
        ride.setStatus(RideStatus.DRIVER_ASSIGNED);
        rideRepository.save(ride);
    }

    @Override
    public ResponseEntity<String> startRide(String driverId, String rideId) {
        updateDriverStatus(driverId, DriverStatus.RIDE_INPROGRESS);
        updateRideStatus(driverId, rideId, RideStatus.IN_PROGRESS);
        return ResponseEntity.ok("Ride started.");
    }

    @Override
    public ResponseEntity<Double> endRide(String driverId, String rideId) {
        updateDriverStatus(driverId, DriverStatus.AVAILABLE);
        Ride ride = saveRideStatus(driverId, rideId, RideStatus.RIDE_COMPLETED);
        double fare = priceCalculationStrategy.calculateFare(ride.getPickUp(), ride.getDrop());
        return ResponseEntity.ok(fare);
    }
}
