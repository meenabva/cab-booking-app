package com.example.cab.booking.repository;

import com.example.cab.booking.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RideRepository extends JpaRepository<Ride, String> {

    @Query("""
            SELECT r FROM Ride r WHERE r.driver.id = :driverId AND r.id = :rideId
            """)
    Optional<Ride> findByDriverIdAndRideId(@Param("driverId") String driverId,
                                          @Param("rideId") String rideId);
}
