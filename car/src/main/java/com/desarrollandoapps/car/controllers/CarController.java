package com.desarrollandoapps.car.controllers;

import com.desarrollandoapps.car.entities.Car;
import com.desarrollandoapps.car.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getCars()
    {
        List<Car> cars = carService.getAll();
        if(cars.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable("id") int id)
    {
        Car car = carService.getCarById(id);
        if(car == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);
    }

    @PostMapping
    public ResponseEntity<Car> save(@RequestBody Car car)
    {
        Car newCar = carService.save(car);
        return ResponseEntity.ok(newCar);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Car>> getCarsByUserId(@PathVariable("userId") int userId)
    {
        List<Car> cars = carService.getCarsByUserId(userId);
        if(cars.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cars);
    }

}
