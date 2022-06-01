package com.desarrollandoapps.moto.contollers;

import com.desarrollandoapps.moto.entities.Moto;
import com.desarrollandoapps.moto.services.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moto")
public class MotoController {

    @Autowired
    private MotoService motoService;

    @GetMapping
    public ResponseEntity<List<Moto>> getAll()
    {
        List<Moto> motos = motoService.getAll();
        if(motos.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Moto> getById(@PathVariable("id") int id)
    {
        Moto moto = motoService.getMotoById(id);
        if(moto == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(moto);
    }

    @PostMapping
    public ResponseEntity<Moto> save(@RequestBody Moto moto)
    {
        Moto newMoto = motoService.save(moto);
        return ResponseEntity.ok(newMoto);

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Moto>> getMotoByUserId(@PathVariable("userId") int userId)
    {
        List<Moto> motos = motoService.getMotoByUserId(userId);
        if(motos.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motos);
    }
}
