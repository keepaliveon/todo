package com.example.todo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Tip {
    @Id
    @GeneratedValue
    private Integer id;

    private String content;
}
