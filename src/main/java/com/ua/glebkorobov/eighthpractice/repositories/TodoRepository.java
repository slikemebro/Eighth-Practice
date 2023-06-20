package com.ua.glebkorobov.eighthpractice.repositories;

import com.ua.glebkorobov.eighthpractice.entities.TodoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends CrudRepository<TodoEntity, Long> {
}
