package com.example.lab11.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "juegos")
@Getter
@Setter
public class Juegos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idjuego", nullable = false)
    private Integer idJuego;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 448)
    private String descripcion;

    @Column(name = "precio")   //no nulo
    private Double precio;

    @Column(name = "image", nullable = false, length = 400)
    private String image;

    @ManyToOne
    @JoinColumn(name = "idgenero")
    private Generos genero;

    @ManyToOne
    @JoinColumn(name = "idplataforma")
    private Plataformas plataforma;

    @ManyToOne
    @JoinColumn(name = "ideditora")
    private Editoras editora;

    @ManyToOne
    @JoinColumn(name = "iddistribuidora")
    private Distribuidoras distribuidora;
}
