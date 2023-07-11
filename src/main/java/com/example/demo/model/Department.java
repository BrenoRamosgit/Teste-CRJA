package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Table(name = "departamento")
@Entity
@Getter
@Setter
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @Column(nullable = false, name = "nome")
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Person> persons;

    @OneToMany(mappedBy = "department")
    private List<Task> tasks;
}
