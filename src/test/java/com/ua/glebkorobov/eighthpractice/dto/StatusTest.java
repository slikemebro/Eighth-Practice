package com.ua.glebkorobov.eighthpractice.dto;

import com.ua.glebkorobov.eighthpractice.entities.TodoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class StatusTest {

    TodoEntity todoEntity;

    @BeforeEach
    void setUp(){
        todoEntity = new TodoEntity("Test", LocalDate.now(), Status.PLANNED);
    }

    @Test
    void testPlanned(){
        assertTrue(todoEntity.getStatus().changeStatus(Status.PROGRESS));
        assertTrue(todoEntity.getStatus().changeStatus(Status.NOTIFIED));
        assertTrue(todoEntity.getStatus().changeStatus(Status.DONE));
        assertTrue(todoEntity.getStatus().changeStatus(Status.CANCELED));
    }

    @Test
    void testProgress(){
        todoEntity.setStatus(Status.PROGRESS);

        assertTrue(todoEntity.getStatus().changeStatus(Status.NOTIFIED));
        assertTrue(todoEntity.getStatus().changeStatus(Status.DONE));
        assertTrue(todoEntity.getStatus().changeStatus(Status.CANCELED));

        assertFalse(todoEntity.getStatus().changeStatus(Status.PROGRESS));
    }

    @Test
    void testNotified(){
        todoEntity.setStatus(Status.NOTIFIED);

        assertTrue(todoEntity.getStatus().changeStatus(Status.DONE));
        assertTrue(todoEntity.getStatus().changeStatus(Status.CANCELED));

        assertFalse(todoEntity.getStatus().changeStatus(Status.PROGRESS));
        assertFalse(todoEntity.getStatus().changeStatus(Status.PLANNED));
    }

    @Test
    void testDone(){
        todoEntity.setStatus(Status.DONE);

        assertFalse(todoEntity.getStatus().changeStatus(Status.CANCELED));
        assertFalse(todoEntity.getStatus().changeStatus(Status.PLANNED));
        assertFalse(todoEntity.getStatus().changeStatus(Status.PROGRESS));
        assertFalse(todoEntity.getStatus().changeStatus(Status.NOTIFIED));
    }

    @Test
    void testCanceled(){
        todoEntity.setStatus(Status.CANCELED);

        assertFalse(todoEntity.getStatus().changeStatus(Status.DONE));
        assertFalse(todoEntity.getStatus().changeStatus(Status.PLANNED));
        assertFalse(todoEntity.getStatus().changeStatus(Status.PROGRESS));
        assertFalse(todoEntity.getStatus().changeStatus(Status.NOTIFIED));
    }
}