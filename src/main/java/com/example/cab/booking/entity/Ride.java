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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", referencedColumnName = "id", nullable = true)
    private User driver;

    @ManyToOne
    @JoinColumn(name = "rider_id", referencedColumnName = "id", nullable = false)
    private User rider;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "x", column = @Column(name = "start_x")),
            @AttributeOverride(name = "y", column = @Column(name = "start_y"))
    })
    private Location pickUp;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "x", column = @Column(name = "drop_x")),
            @AttributeOverride(name = "y", column = @Column(name = "drop_y"))
    })
    private Location drop;

    private float fare;

    @Enumerated(EnumType.STRING)
    private RideStatus status;
}
