package edu.pucp.gtics.lab11_gtics_20232.entity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Entity
@Table(name = "juegos")
public class Juegos {

    @Id
    private int idJuego;

    @Size(min = 3, max = 45, message = "Debe contener entre 3 y 45 caracteres")
    private String nombre;

    @Size(min = 3, max = 400, message = "Debe contener entre 3 y 400 caracteres")
    private String descripcion;

    @DecimalMin(value = "10" , message = "Valor mínimo 10")
    @DecimalMax(value = "500" , message = "Valor máximo 500")
    private double precio;

    private String image;

    public int getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(int idJuego) {
        this.idJuego = idJuego;
    }

    public Editoras getEditora() {
        return editora;
    }

    public void setEditora(Editoras editora) {
        this.editora = editora;
    }

    @ManyToOne
    @JoinColumn(name = "idplataforma")
    @Valid
    private Plataformas plataforma;

    @ManyToOne
    @JoinColumn(name = "iddistribuidora")
    @Valid
    private Distribuidoras distribuidora;

    @ManyToOne
    @JoinColumn(name = "idgenero")
    @Valid
    private Generos genero;

    @ManyToOne
    @JoinColumn(name="ideditora")
    @Valid
    private Editoras editora;




    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Plataformas getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(Plataformas plataforma) {
        this.plataforma = plataforma;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Distribuidoras getDistribuidora() {
        return distribuidora;
    }

    public void setDistribuidora(Distribuidoras distribuidora) {
        this.distribuidora = distribuidora;
    }

    public Generos getGenero() {
        return genero;
    }

    public void setGenero(Generos genero) {
        this.genero = genero;
    }
}
