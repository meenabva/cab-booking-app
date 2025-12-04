package com.example.cab.booking.strategy;

import com.example.cab.booking.entity.Location;

public interface PriceCalculationStrategy {

    public double calculateFare(Location pickUp, Location drop);
}
