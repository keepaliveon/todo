package com.example.todo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Admin {
    @Id
    private String username;

    private String password;
}
