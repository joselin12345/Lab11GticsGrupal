package edu.pucp.gtics.lab11_gtics_20232.entity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Entity
@Table(name = "distribuidoras")
public class Distribuidoras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 0, message = "Distribuidora no puede estar vacío")
    private int idDistribuidora;

    @Size(min=3, max = 50, message = "Debe contener entre 3 y 50 caracteres")
    private String nombre;

    @Size(min=3, max = 198, message = "Debe contener entre 3 y 198 caracteres")
    private String descripcion;

    @Size(min=3, max = 198, message = "Debe contener entre 3 y 198 caracteres")
    @Pattern(regexp = "^(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", message = "Debe ser una URL http o https")
    private String web;

    @Min(value = 1800, message = "Debe ser mayor que o igual a 1800")
    @Max(value = 2100, message = "Debe ser menor que 2100")
    @NotNull(message = "Coloque un número")
    private int fundacion = 1800;

    @ManyToOne
    @JoinColumn(name = "idsede")
    @Valid
    private Paises sede;

    public int getIdDistribuidora() {
        return idDistribuidora;
    }

    public void setIdDistribuidora(int idDistribuidora) {
        this.idDistribuidora = idDistribuidora;
    }

    public Paises getSede() {
        return sede;
    }

    public void setSede(Paises sede) {
        this.sede = sede;
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

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
    public int getFundacion() {
        return fundacion;
    }

    public void setFundacion(int fundacion) {
        this.fundacion = fundacion;
    }


}
