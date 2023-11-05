package edu.pucp.gtics.lab11_gtics_20232.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "editoras")
public class Editoras {
    public int getIdEditora() {
        return idEditora;
    }

    public void setIdEditora(int idEditora) {
        this.idEditora = idEditora;
    }

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

    @Id
    private int idEditora;

    @Size(min = 3, max = 50, message = "Debe contener entre 3 y 45 caracteres")
    private String nombre;

    @Size(min = 3, max = 200, message = "Debe contener entre 3 y 400 caracteres")
    private String descripcion;


}
