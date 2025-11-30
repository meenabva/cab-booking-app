package com.example.cab.booking.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue("DRIVER")
public class Driver extends User {

    private Location location;
    private DriverStatus status;
    private String vehicleNumber;
}
