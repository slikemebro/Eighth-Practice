package com.ua.glebkorobov.eighthpractice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ua.glebkorobov.eighthpractice.dto.Status;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "todo")
@Data
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotBlank(message = "{is_blank}")
    private String task;

    @Future(message = "{not_future}")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "{not_null}")
    private Status status;

    public TodoEntity(String task, LocalDate date, Status status) {
        this.task = task;
        this.date = date;
        this.status = status;
    }

    public TodoEntity() {
    }
}
