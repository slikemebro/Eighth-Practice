package com.ua.glebkorobov.eighthpractice.dto;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class TodoDto {

    @NotBlank(message = "{is_blank}")
    private String task;

    @Future(message = "{not_future}")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "{not_null}")
    private Status status;

    public TodoDto(String task, LocalDate date, Status status) {
        this.task = task;
        this.date = date;
        this.status = status;
    }

    public TodoDto() {
    }
}
