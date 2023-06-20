package com.ua.glebkorobov.eighthpractice.controllers;

import com.ua.glebkorobov.eighthpractice.dto.Status;
import com.ua.glebkorobov.eighthpractice.entities.TodoEntity;
import com.ua.glebkorobov.eighthpractice.services.TodoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    @RolesAllowed({"ADMIN", "USER"})
    public List<TodoEntity> getTodoList(){
        return todoService.getTodoList();
    }

    @GetMapping("{id}")
    @RolesAllowed({"ADMIN", "USER"})
    public TodoEntity getTodoById(@PathVariable("id") long id){
        return todoService.getOneTask(id);
    }

    @PostMapping
    @RolesAllowed({"ADMIN", "USER"})
    public ResponseEntity<String> postTodoTask(@Valid @RequestBody TodoEntity todoEntity){
        return todoService.save(todoEntity);
    }

    @PutMapping("{id}")
    @RolesAllowed({"ADMIN", "USER"})
    public ResponseEntity<String> putTodoTask(@PathVariable("id") long id,
                                              @RequestParam(required = false) String task,
                                              @RequestParam(required = false) LocalDate date,
                                              @RequestParam(required = false) Status status){

        return todoService.changeStatus(todoService.getOneTask(id), task, date, status);
    }

    @DeleteMapping("{id}")
    @RolesAllowed("ADMIN")
    public ResponseEntity<String> deleteTodoTask(@PathVariable("id") long id){
        return todoService.deleteTaskById(id);
    }
}
