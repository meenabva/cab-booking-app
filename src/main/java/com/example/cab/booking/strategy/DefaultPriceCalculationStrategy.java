package com.example.cab.booking.strategy;

import com.example.cab.booking.entity.Location;
import org.springframework.stereotype.Service;

@Service
public class DefaultPriceCalculationStrategy implements PriceCalculationStrategy {

    private static final double BASE_FARE = 50.0;
    private static final double COST_PER_KM = 10.0;


    @Override
    public double calculateFare(Location pickUp, Location drop) {
        double distance = Location.findDistance(pickUp, drop);
        return BASE_FARE + (COST_PER_KM * distance);
    }
}
