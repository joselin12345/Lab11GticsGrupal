package com.example.lab11.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "paises")
@Getter
@Setter
public class Paises {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpais", nullable = false)
    private Integer idPais;

    @Column(name = "iso", nullable = false, length = 2)
    private String iso;

    @Column(name = "nombre", nullable = false, length = 80)
    private String nombre;

}

