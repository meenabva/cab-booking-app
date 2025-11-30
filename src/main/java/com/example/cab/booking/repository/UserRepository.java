package com.example.cab.booking.repository;

import com.example.cab.booking.entity.Driver;
import com.example.cab.booking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("""
            SELECT * FROM USER 
            WHERE userType = 'DRIVER'  
            AND status = 'AVAILABLE' 
            """)
    List<Driver> findAllAvailableDrivers();
}
