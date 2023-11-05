package edu.pucp.gtics.lab11_gtics_20232.entity;


import javax.persistence.*;
import javax.validation.Valid;

@Entity
@Table(name = "juegosxusuario")
public class JuegosxUsuario {

    @Id
    private int idJuegoXUsuario;

    @ManyToOne
    @JoinColumn(name = "idjuego")
    @Valid
    private Juegos juego;

    @ManyToOne
    @JoinColumn(name = "idusuario")
    @Valid
    private User usuario;

    private int cantidad;


    public int getIdJuegoXUsuario() {return idJuegoXUsuario;}
    public void setIdJuegoXUsuario(int idJuegoXUsuario) {this.idJuegoXUsuario = idJuegoXUsuario;}

    public Juegos getJuego() {
        return juego;
    }
    public void setJuego(Juegos juego) {
        this.juego = juego;
    }

    public User getUsuario() {
        return usuario;
    }
    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
