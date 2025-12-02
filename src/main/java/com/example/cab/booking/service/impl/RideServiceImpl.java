package com.example.cab.booking.service.impl;

import com.example.cab.booking.entity.Driver;
import com.example.cab.booking.entity.Ride;
import com.example.cab.booking.entity.RideStatus;
import com.example.cab.booking.entity.User;
import com.example.cab.booking.repository.RideRepository;
import com.example.cab.booking.repository.UserRepository;
import com.example.cab.booking.service.DriverService;
import com.example.cab.booking.service.RideService;
import com.example.cab.booking.service.dto.RideDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RideServiceImpl implements RideService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private DriverService driverService;

    @Override
    public ResponseEntity<Ride> bookRide(String riderId, RideDTO rideDTO) {
        User rider = userRepository.findById(riderId)
                .orElseThrow(() -> new RuntimeException("Rider not found."));
        Ride ride = new Ride();
        ride.setRider(rider);
        ride.setStart(rideDTO.pickupLocation());
        ride.setDrop(rideDTO.dropLocation());
        ride.setStatus(RideStatus.BOOKED);
        ride = rideRepository.save(ride);
        driverService.assignClosestDriver(ride);
        return ResponseEntity.ok(ride);
    }

    @Override
    public ResponseEntity<Ride> updateRideStatus(String rideId, String status) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found."));
        ride.setStatus(RideStatus.valueOf(status));
        ride = rideRepository.save(ride);
        return ResponseEntity.ok(ride);
    }

    @Override
    public ResponseEntity<Ride> getRideDetails(String rideId) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found."));
        return ResponseEntity.ok(ride);
    }
}
