package com.example.cab.booking.service.dto;

import com.example.cab.booking.entity.Location;

public record RideDTO(Location pickupLocation, Location dropLocation) {
}
