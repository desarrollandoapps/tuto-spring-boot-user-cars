package com.desarrollandoapps.car.services;

import com.desarrollandoapps.car.entities.Car;
import com.desarrollandoapps.car.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAll()
    {
        return carRepository.findAll();
    }

    public Car getCarById(int id)
    {
        return carRepository.findById(id).orElse(null);
    }

    public Car save(Car car)
    {
        return carRepository.save(car);
    }

    public List<Car> getCarsByUserId(int userId)
    {
        return carRepository.findByUserId(userId);
    }
}
