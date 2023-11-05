package com.example.lab11.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "distribuidoras")
@Getter
@Setter
public class Distribuidoras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddistribuidora", nullable = false)
    private Integer idDistribuidora;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 200)
    private String descripcion;

    @Column(name = "fundacion")   //no nulo
    private Integer fundacion;

    @Column(name = "web", nullable = false, length = 200)
    private String web;

    @ManyToOne
    @JoinColumn(name = "idsede")
    private Paises sede;

}
