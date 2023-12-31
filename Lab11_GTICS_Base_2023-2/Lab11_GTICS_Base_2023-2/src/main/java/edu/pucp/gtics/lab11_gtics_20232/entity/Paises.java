package edu.pucp.gtics.lab11_gtics_20232.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Min;

@Entity
@Table(name = "paises")
public class Paises {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 0, message = "Sede no puede estar vacío")
    private int idPais;
    private String iso;
    private String nombre;

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
