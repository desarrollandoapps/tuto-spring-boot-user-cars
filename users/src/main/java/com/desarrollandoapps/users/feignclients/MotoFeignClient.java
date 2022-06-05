package com.desarrollandoapps.users.feignclients;

import com.desarrollandoapps.users.models.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "moto-service", url = "http://localhost:8003")
@RequestMapping("/moto")
public interface MotoFeignClient {
    @PostMapping()
    public Moto save(@RequestBody Moto moto);

    @GetMapping("/user/{userId}")
    public List<Moto> getMotos(@PathVariable("userId") int userId);
}
