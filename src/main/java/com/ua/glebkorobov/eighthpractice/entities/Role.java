package com.ua.glebkorobov.eighthpractice.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Role(String name) {
        this.name = name;
    }

    public Role() {

    }
}
