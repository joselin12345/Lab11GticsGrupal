package edu.pucp.gtics.lab11_gtics_20232.repository;

import edu.pucp.gtics.lab11_gtics_20232.entity.Juegos;
import edu.pucp.gtics.lab11_gtics_20232.entity.JuegosxUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JuegosXUsuarioRepository extends JpaRepository<JuegosxUsuario,Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM juegosxusuario jxu\n" +
            "inner join juegos j on j.idjuego = jxu.idjuego \n" +
            "where jxu.idusuario = ?1")
    List<JuegosxUsuario> listaJuegosXUsuario(int idUsuario);
}
