package edu.pucp.gtics.lab11_gtics_20232.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
@Table(name = "juegosxusuario")
@Getter
@Setter
public class JuegosxUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idjuegosxusario", nullable = false)
    private Integer idjuegosxusuario;

    @ManyToOne
    @JoinColumn(name = "idjuego", nullable = false)
    private Juegos juego;

    @ManyToOne
    @JoinColumn(name = "idusuario", nullable = false)
    private User idusuario;

    @Column(name = "cantidad")
    private Integer cantidad;
}