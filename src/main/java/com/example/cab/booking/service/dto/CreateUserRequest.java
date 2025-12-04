package com.example.cab.booking.service.dto;

import com.example.cab.booking.entity.DriverStatus;
import com.example.cab.booking.entity.Location;
import lombok.Data;

/**
 * DTO for creating a user (Rider or Driver).
 * userType should be either "RIDER" or "DRIVER" (case-insensitive).
 */
@Data
public class CreateUserRequest {
    private String name;
    private String userType;

    // Driver-specific fields (optional for riders)
    private Location location;
    private DriverStatus status;
    private String vehicleNumber;
}

