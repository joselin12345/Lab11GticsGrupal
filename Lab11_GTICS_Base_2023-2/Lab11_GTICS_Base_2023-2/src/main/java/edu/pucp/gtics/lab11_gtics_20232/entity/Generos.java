package edu.pucp.gtics.lab11_gtics_20232.entity;


import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "generos")

public class Generos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 0, message = "Género no puede estar vacío")
    private
    int idGenero;

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    private String nombre;
    private String descripcion;


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
}
