package com.example.cab.booking.rest;

import com.example.cab.booking.entity.Location;
import com.example.cab.booking.entity.Ride;
import org.springframework.web.bind.annotation.*;

@RestController("/api/{riderId}/ride")
public class RideController {

    @PostMapping("/new")
    public Ride bookRide(@PathVariable String riderId,
                           @RequestBody Location startLocation){
        return null;
    }

    @GetMapping("/{rideId}")
    public Ride getRideDetails(@PathVariable String rideId){
        return null;
    }

    @PatchMapping("/{rideId}/cancel")
    public String cancelRide(@PathVariable String rideId)
    {
        return "ride cancelled";
    }
}
