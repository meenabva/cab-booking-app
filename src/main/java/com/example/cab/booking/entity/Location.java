package com.example.cab.booking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class Location {

    private double x;
    private double y;

    public static double findDistance(Location loc1, Location loc2){
        return Math.sqrt(Math.pow(loc1.getX() - loc2.getX(), 2) +
                Math.pow(loc1.getY() - loc2.getY(), 2));
    }
}
