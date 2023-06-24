package com.ua.glebkorobov.eighthpractice.controllers;

import com.ua.glebkorobov.eighthpractice.dto.Status;
import com.ua.glebkorobov.eighthpractice.dto.TodoDto;
import com.ua.glebkorobov.eighthpractice.entities.TodoEntity;
import com.ua.glebkorobov.eighthpractice.services.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/todo")
@Tag(name = "Todo controller", description = "Methods for working with todo list")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("{page}/{size}")
    @Operation(summary = "Get all tasks")
    @RolesAllowed({"ADMIN", "USER"})
    public List<TodoEntity> getTodoList(
            @Parameter(description = "Number of page")
            @RequestParam("page") int page,
            @Parameter(description = "Size of page")
            @RequestParam("size") int size) {
        return todoService.getTodoList(page, size);
    }

    @GetMapping("{id}")
    @Operation(summary = "Get task by id")
    @RolesAllowed({"ADMIN", "USER"})
    public TodoEntity getTodoById(
            @Parameter(description = "Id of task")
            @PathVariable("id") long id) {
        return todoService.getOneTask(id);
    }

    @PostMapping
    @Operation(summary = "Post task")
    @RolesAllowed({"ADMIN", "USER"})
    public ResponseEntity<String> postTodoTask(
            @Parameter(description = "Posted task")
            @Valid @RequestBody TodoDto todoDto) {
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setDate(todoDto.getDate());
        todoEntity.setTask(todoDto.getTask());
        todoEntity.setStatus(todoDto.getStatus());
        return todoService.save(todoEntity);
    }

    @PutMapping("{id}")
    @Operation(summary = "Put task")
    @RolesAllowed({"ADMIN", "USER"})
    public ResponseEntity<String> putTodoTask(
            @Parameter(description = "Id of putted task")
            @PathVariable("id") long id,
            @Parameter(description = "Putted task")
            @RequestParam(required = false) String task,
            @Parameter(description = "Putted date")
            @RequestParam(required = false) LocalDate date,
            @Parameter(description = "Putted status")
            @RequestParam(required = false) Status status) {

        return todoService.changeStatus(todoService.getOneTask(id), task, date, status);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete task")
    @RolesAllowed("ADMIN")
    public ResponseEntity<String> deleteTodoTask(
            @Parameter(description = "Id of deleted task")
            @PathVariable("id") long id) {
        return todoService.deleteTaskById(id);
    }
}
