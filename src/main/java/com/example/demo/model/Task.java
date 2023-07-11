package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "tarefa")
@Entity
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    public Long id;

    @Column(nullable = false, name = "titulo")
    public String title;

    @Column(nullable = false, name = "descricao")
    public String description;

    @Column(nullable = false, name = "prazo")
    public LocalDate term;

    @Column(nullable = false, name = "data_hora_inicio")
    public LocalDateTime startTime;

    @Column(nullable = false, name = "data_hora_fim")
    public LocalDateTime finishTime;

    @Column(nullable = false, name = "duracao")
    public Integer duration;

    @Column(nullable = false, name = "finalizado")
    public Boolean finished;

    @JoinColumn(name = "departamento_id")
    @OneToOne
    public Department department;

    @JoinColumn(name = "pessoa_id")
    @OneToOne
    public Person person;

}
