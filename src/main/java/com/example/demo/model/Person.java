package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "pessoa")
@Entity
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    public Long id;

    @Column(nullable = false, name = "nome")
    public String name;

    @JoinColumn(name = "departamento_id")
    @OneToOne
    public Departament departament;

}
