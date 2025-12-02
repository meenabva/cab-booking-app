package com.example.cab.booking.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue("DRIVER")
public class Driver extends User {

    @Embedded
    private Location location;

    @Enumerated
    private DriverStatus status;

    private String vehicleNumber;
}
