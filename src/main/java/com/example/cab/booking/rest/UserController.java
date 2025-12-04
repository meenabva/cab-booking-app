package com.example.cab.booking.rest;

import com.example.cab.booking.entity.Driver;
import com.example.cab.booking.entity.Rider;
import com.example.cab.booking.entity.User;
import com.example.cab.booking.repository.UserRepository;
import com.example.cab.booking.service.dto.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest request){
        String type = request.getUserType() == null ? "RIDER" : request.getUserType().trim().toUpperCase();
        User user;
        if("DRIVER".equals(type)){
            Driver driver = new Driver();
            driver.setName(request.getName());
            driver.setLocation(request.getLocation());
            driver.setStatus(request.getStatus());
            driver.setVehicleNumber(request.getVehicleNumber());
            user = driver;
        } else {
            Rider rider = new Rider();
            rider.setName(request.getName());
            user = rider;
        }
        User saved = userRepository.save(user);
        return ResponseEntity.ok(saved);
    }
}

