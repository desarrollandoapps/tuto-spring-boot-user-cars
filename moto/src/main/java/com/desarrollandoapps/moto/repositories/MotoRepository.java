package com.desarrollandoapps.moto.repositories;

import com.desarrollandoapps.moto.entities.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Integer> {

    List<Moto> findByUserId(int userId);
}
