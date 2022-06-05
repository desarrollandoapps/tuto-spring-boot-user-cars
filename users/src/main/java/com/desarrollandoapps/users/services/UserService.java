package com.desarrollandoapps.users.services;

import com.desarrollandoapps.users.entities.User;
import com.desarrollandoapps.users.feignclients.CarFeignClient;
import com.desarrollandoapps.users.feignclients.MotoFeignClient;
import com.desarrollandoapps.users.models.Car;
import com.desarrollandoapps.users.models.Moto;
import com.desarrollandoapps.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarFeignClient carFeignClient;

    @Autowired
    private MotoFeignClient motoFeignClient;

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

    // Guardar carro utilizando Feing
    public Car saveCar(int userId, Car car)
    {
        car.setUserId(userId); // Agregar el usuario al carro
        Car newCar =  carFeignClient.save(car);
        return newCar;
    }

    // Guardar moto utilizando Feing
    public Moto saveMoto(int userId, Moto moto)
    {
        moto.setUserId(userId); // Agregar el usuario al carro
        Moto newMoto =  motoFeignClient.save(moto);
        return newMoto;
    }

    // Listar vehículos de un usuario
    public Map<String, Object> getUserAndVehicles(int userId)
    {
        Map<String, Object> resultado = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);
        if(user == null)
        {
            resultado.put("Mensaje", "No existe el usuario");
            return resultado;
        }
        resultado.put("User", user);
        List<Car> cars = carFeignClient.getCars(userId);
        if(cars == null)
        {
            resultado.put("cars", "El usuario no tiene carros");
        } else {
            resultado.put("cars", cars);
        }
        List<Moto> motos = motoFeignClient.getMotos(userId);
        if(motos == null)
        {
            resultado.put("motos", "El usuario no tiene carros");
        } else {
            resultado.put("motos", motos);
        }

        return resultado;
    }


}
