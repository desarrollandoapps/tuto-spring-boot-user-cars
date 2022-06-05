package com.desarrollandoapps.users.controllers;

import com.desarrollandoapps.users.entities.User;
import com.desarrollandoapps.users.models.Car;
import com.desarrollandoapps.users.models.Moto;
import com.desarrollandoapps.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> listarUsuarios(){
        List<User> usuarios = userService.getAll();
        if(usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int id){
        User user = userService.getUserById(id);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User usuario){
        User nuevoUsuario = userService.save(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    // Obtiene el listado de carros de un usuario
    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getCarsByUser(@PathVariable("userId") int userId)
    {
        User user = userService.getUserById(userId);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        List<Car> cars = userService.getCars(userId);
        return ResponseEntity.ok(cars);
    }

    // Obtiene el listado de motos de un usuario
    @GetMapping("/motos/{userId}")
    public ResponseEntity<List<Moto>> getMotosByUser(@PathVariable("userId") int userId)
    {
        User user = userService.getUserById(userId);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        List<Moto> motos = userService.getMotos(userId);
        return ResponseEntity.ok(motos);
    }

    // Comunica con el microservicio car-service por medio de Feign
    @PostMapping("/car/{userId}")
    public ResponseEntity<Car> saveCarUser(@PathVariable("userId") int userId, @RequestBody Car car)
    {
        Car newCar = userService.saveCar(userId, car);
        return ResponseEntity.ok(newCar);
    }

    // Comunica con el microservicio moto-service por medio de Feign
    @PostMapping("/moto/{userId}")
    public ResponseEntity<Moto> saveMotoUser(@PathVariable("userId") int userId, @RequestBody Moto moto)
    {
        Moto newMoto = userService.saveMoto(userId, moto);
        return ResponseEntity.ok(newMoto);
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<Map<String, Object>> getAllVehicles(@PathVariable("userId") int userId)
    {
        Map<String, Object> resultado = userService.getUserAndVehicles(userId);
        return ResponseEntity.ok(resultado);
    }
}


























