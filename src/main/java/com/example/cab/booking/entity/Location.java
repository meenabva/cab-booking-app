package com.example.cab.booking.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Location {

    private double x;
    private double y;

    public static double findDistance(Location loc1, Location loc2){
        return Math.sqrt(Math.pow(loc1.getX() - loc2.getX(), 2) +
                Math.pow(loc1.getY() - loc2.getY(), 2));
    }
}
