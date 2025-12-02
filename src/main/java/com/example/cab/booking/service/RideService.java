package com.example.cab.booking.service;

import com.example.cab.booking.entity.Ride;
import com.example.cab.booking.service.dto.RideDTO;
import org.springframework.http.ResponseEntity;

public interface RideService {

    public ResponseEntity<Ride> bookRide(String riderId, RideDTO rideDTO);

    public ResponseEntity<Ride> updateRideStatus(String rideId, String status);

    public ResponseEntity<Ride> getRideDetails(String rideId);

}
