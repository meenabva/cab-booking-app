package com.example.cab.booking.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToMany
    @JoinColumn(name = "driver_id", referencedColumnName = "id", nullable = false)
    private User driver;

    @OneToMany
    @JoinColumn(name = "rider_id", referencedColumnName = "id", nullable = false)
    private User rider;

    @Embedded
    private Location start;

    @Embedded
    private Location drop;

    private float fare;

    @Enumerated
    private RideStatus status;
}
