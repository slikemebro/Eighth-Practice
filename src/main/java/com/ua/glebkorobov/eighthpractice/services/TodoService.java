package com.ua.glebkorobov.eighthpractice.services;

import com.ua.glebkorobov.eighthpractice.dto.Status;
import com.ua.glebkorobov.eighthpractice.entities.TodoEntity;
import com.ua.glebkorobov.eighthpractice.exceptions.EntityNotValidException;
import com.ua.glebkorobov.eighthpractice.exceptions.InvalidStatusException;
import com.ua.glebkorobov.eighthpractice.exceptions.NotFoundException;
import com.ua.glebkorobov.eighthpractice.repositories.TodoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private MessageSource messageSource;


    public List<TodoEntity> getTodoList() {
        List<TodoEntity> todoEntities = new ArrayList<>();
        todoRepository.findAll().forEach(todoEntities::add);
        return todoEntities;
    }

    public TodoEntity getOneTask(long id) {
        Optional<TodoEntity> todoEntity = todoRepository.findById(id);
        if (todoEntity.isPresent()) {
            return todoEntity.get();
        } else {
            throw new NotFoundException(
                    messageSource.getMessage("not_found", null, LocaleContextHolder.getLocale()));
        }
    }

    public ResponseEntity<String> save(TodoEntity todoEntity) {
        todoRepository.save(todoEntity);

        String messageTask = messageSource.getMessage("task", null, LocaleContextHolder.getLocale());
        String messagePosted = messageSource.getMessage("posted", null, LocaleContextHolder.getLocale());
        return ResponseEntity.status(HttpStatus.CREATED).body(messageTask + todoEntity + messagePosted);
    }

    public ResponseEntity<String> deleteTaskById(long id) {
        Optional<TodoEntity> todoEntityOptional = todoRepository.findById(id);
        if (todoEntityOptional.isPresent()) {
            todoRepository.deleteById(id);

            String message = messageSource.getMessage("deleted_todo", null, LocaleContextHolder.getLocale());
            return ResponseEntity.status(HttpStatus.OK).body(message + todoEntityOptional.get());
        }
        throw new NotFoundException(
                messageSource.getMessage("not_found", null, LocaleContextHolder.getLocale())
        );
    }

    public ResponseEntity<String> changeStatus(TodoEntity todoEntity, String task, LocalDate date, Status status) {
        if (task != null) {
            todoEntity.setTask(task);
        }
        if (date != null) {
            todoEntity.setDate(date);
        }

        Status todoEntityStatus = todoEntity.getStatus();
        if (status != null && todoEntityStatus.changeStatus(status)) {
            todoEntity.setStatus(status);
        } else {
            throw new InvalidStatusException(
                    messageSource.getMessage("invalid_status", null, LocaleContextHolder.getLocale())
            );
        }

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        if (!validator.validate(todoEntity).isEmpty()) {
            throw new EntityNotValidException(
                    messageSource.getMessage("invalid_todo_entity", null, LocaleContextHolder.getLocale())
            );
        } else {
            todoRepository.save(todoEntity);
        }

        String message = messageSource.getMessage("changed_to", null, LocaleContextHolder.getLocale());

        return ResponseEntity.status(HttpStatus.OK).body(message + todoEntity);
    }
}