package com.desarrollandoapps.users.repositories;

import com.desarrollandoapps.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
