package com.desarrollandoapps.users.services;

import com.desarrollandoapps.users.entities.User;
import com.desarrollandoapps.users.models.Car;
import com.desarrollandoapps.users.models.Moto;
import com.desarrollandoapps.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User usuario) {
        User nuevoUsuario = userRepository.save(usuario);
        return nuevoUsuario;
    }

    // Consumir el servicio que entrega la lista de carros de un usuario - RestTemplate
    public List<Car> getCars(int userId)
    {
        List<Car> cars = restTemplate.getForObject("http://localhost:8002/car/user/" + userId, List.class);
        return cars;
    }

    // Consumir el servicio que entrega la lista de motos de un usuario - RestTemplate
    public List<Moto> getMotos(int userId)
    {
        List<Moto> motos = restTemplate.getForObject("http://localhost:8003/moto/user/" + userId, List.class);
        return motos;
    }


}
