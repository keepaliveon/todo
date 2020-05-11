package com.example.todo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Catalog {

    @Id
    @GeneratedValue
    private Integer id;

    private String text;

    @ManyToOne
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "catalog")
    @JsonIgnore
    private List<Todo> todoList = new ArrayList<>();
}
