package com.example.lab11.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "juegosxusuario")
@Getter
@Setter
public class Juegosxusuario {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idjuegosxusario", nullable = false)
        private Integer idjuegoxusuario;

        @ManyToOne
        @JoinColumn(name = "idjuego", nullable = false)
        private Juegos idjuego;

        @ManyToOne
        @JoinColumn(name = "idusuario", nullable = false)
        private Usuarios idusuario;

        @Column(name = "cantidad")
        private Integer cantidad;
}
