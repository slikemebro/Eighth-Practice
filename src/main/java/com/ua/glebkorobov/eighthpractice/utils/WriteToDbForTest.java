package com.ua.glebkorobov.eighthpractice.utils;

import com.ua.glebkorobov.eighthpractice.entities.Role;
import com.ua.glebkorobov.eighthpractice.entities.TodoEntity;
import com.ua.glebkorobov.eighthpractice.entities.User;
import com.ua.glebkorobov.eighthpractice.repositories.RoleRepository;
import com.ua.glebkorobov.eighthpractice.repositories.UserRepository;
import com.ua.glebkorobov.eighthpractice.services.TodoService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static com.ua.glebkorobov.eighthpractice.dto.Status.PLANNED;

@Component
public class WriteToDbForTest {

    private final TodoService service;

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    public WriteToDbForTest(TodoService service, RoleRepository roleRepository, UserRepository userRepository) {
        this.service = service;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Bean
    private void createTasks() {
        TodoEntity task1 = new TodoEntity(
                "Todo any",
                LocalDate.of(2024, 5, 10),
                PLANNED);
        TodoEntity task2 = new TodoEntity(
                "Todo else any",
                LocalDate.of(2024, 11, 11),
                PLANNED);

        service.save(task1);
        service.save(task2);
    }

    @Bean
    private void createUsersAndRoles() {
        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");

        User user1 = new User("admin", "$2a$12$scuKnDo.zotoXe/gp8CTz.Ho8aJctP8AJuL/DdTT55ZgHlBRRGyyS",
                List.of(role1, role2));
        User user2 = new User("user", "$2a$12$scuKnDo.zotoXe/gp8CTz.Ho8aJctP8AJuL/DdTT55ZgHlBRRGyyS",
                List.of(role2));

        roleRepository.save(role1);
        roleRepository.save(role2);

        userRepository.save(user1);
        userRepository.save(user2);
    }
}
