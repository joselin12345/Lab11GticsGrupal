package com.example.lab11.repository;

import com.example.lab11.entity.Juegosxusuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface JuegosxusuarioRepository extends JpaRepository<Juegosxusuario, Integer> {
    @Query(value = "SELECT j.idjuego, j.nombre, j.descripcion, j.precio, p.nombre, d.nombre\n" +
            "FROM juegos j\n" +
            "JOIN plataformas p ON j.idplataforma = p.idplataforma\n" +
            "JOIN distribuidoras d ON j.iddistribuidora = d.iddistribuidora\n" +
            "JOIN juegosxusuario ju ON j.idjuego = ju.idjuego\n" +
            "WHERE ju.idusuario = :idusuario", nativeQuery = true)
    List<Object[]> findJuegosByUsuarioId(@Param("idusuario") int userId);
}

