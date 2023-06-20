package com.ua.glebkorobov.eighthpractice.repositories;

import com.ua.glebkorobov.eighthpractice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

}
