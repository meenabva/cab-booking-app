package com.example.cab.booking.rest;

import com.example.cab.booking.entity.Location;
import com.example.cab.booking.entity.Ride;
import com.example.cab.booking.entity.RideStatus;
import com.example.cab.booking.service.RideService;
import com.example.cab.booking.service.dto.RideDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/{riderId}/ride")
public class RideController {

    @Autowired
    private RideService rideService;

    @PostMapping("/new")
    public ResponseEntity<Ride> bookRide(@PathVariable String riderId,
                                        @RequestBody RideDTO rideDTO){
        return rideService.bookRide(riderId, rideDTO);
    }

    @GetMapping("/{rideId}")
    public ResponseEntity<Ride> getRideDetails(@PathVariable String rideId){
        return rideService.getRideDetails(rideId);
    }

    @PatchMapping("/{rideId}/cancel")
    public ResponseEntity<Ride> cancelRide(@PathVariable String rideId) {
        return rideService.updateRideStatus(rideId, String.valueOf(RideStatus.CANCELED));
    }
}
