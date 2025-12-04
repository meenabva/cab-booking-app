package com.example.cab.booking.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue("DRIVER")
public class Driver extends User {

    @Embedded
    private Location location;

    @Enumerated(EnumType.STRING)
    private DriverStatus status;

    private String vehicleNumber;
}
