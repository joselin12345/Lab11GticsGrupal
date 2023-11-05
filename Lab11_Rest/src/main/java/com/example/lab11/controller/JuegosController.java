package com.example.lab11.controller;

import com.example.lab11.entity.Juegos;
import com.example.lab11.entity.Juegosxusuario;
import com.example.lab11.repository.JuegosRepository;
import com.example.lab11.repository.JuegosxusuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/juegos")
public class JuegosController {

    final JuegosRepository juegosRepository;
    final JuegosxusuarioRepository juegosxusuarioRepository;

    public JuegosController(JuegosRepository juegosRepository, JuegosxusuarioRepository juegosxusuarioRepository) {
        this.juegosRepository = juegosRepository;
        this.juegosxusuarioRepository = juegosxusuarioRepository;
    }

    @GetMapping(value =  "")
    public List<Juegos> listarJuegos() {
        return juegosRepository.findAll();
    }

    @PostMapping(value = {"", "/guardar"})
    public ResponseEntity<HashMap<String, Object>> crearJuego(
            @RequestBody Juegos juego,   //para crear se usa body en postman - raw
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {  //pa que devuelva o no id: ...8080/product?fetchid=true

        HashMap<String, Object> responseJson = new HashMap<>();

        juegosRepository.save(juego);
        if (fetchId) {
            responseJson.put("id", juego.getIdJuego());
        }
        responseJson.put("estado", "creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);
    }

    @PutMapping(value = { "","/actualizar"})
    public ResponseEntity<HashMap<String, Object>> actualizarJuego(@RequestBody Juegos juegoRecibido) {

        HashMap<String, Object> rpta = new HashMap<>();

        if (juegoRecibido.getIdJuego() != null && juegoRecibido.getIdJuego() > 0) {

            Optional<Juegos> byId = juegosRepository.findById(juegoRecibido.getIdJuego());
            if (byId.isPresent()) {
                Juegos juegoActualizado = byId.get();

                if (juegoRecibido.getNombre() != null)
                    juegoActualizado.setNombre(juegoRecibido.getNombre());

                if (juegoRecibido.getDescripcion() != null)
                    juegoActualizado.setDescripcion(juegoRecibido.getDescripcion());

                if (juegoRecibido.getPrecio() != null)
                    juegoActualizado.setPrecio(juegoRecibido.getPrecio());

                if (juegoRecibido.getImage() != null)
                    juegoActualizado.setImage(juegoRecibido.getImage());

                if (juegoRecibido.getGenero() != null)
                    juegoActualizado.setGenero(juegoRecibido.getGenero());

                if (juegoRecibido.getPlataforma() != null)
                    juegoActualizado.setPlataforma(juegoRecibido.getPlataforma());

                if (juegoRecibido.getEditora() != null)
                    juegoActualizado.setEditora(juegoRecibido.getEditora());

                if (juegoRecibido.getDistribuidora() != null)
                    juegoActualizado.setDistribuidora(juegoRecibido.getDistribuidora());

                juegosRepository.save(juegoActualizado);
                rpta.put("resultado", "ok");
                rpta.put("msg", "El juego ha sido actualizado");
                return ResponseEntity.ok(rpta);
            } else {
                rpta.put("resultado", "error");
                rpta.put("msg", "El ID del juego ingresado no existe");
                return ResponseEntity.badRequest().body(rpta);
            }
        } else {
            rpta.put("resultado", "error");
            rpta.put("msg", "Debe ingresar un juego con ID");
            return ResponseEntity.badRequest().body(rpta);
        }
    }


    @DeleteMapping("/borrar")                                                                           //con PathVariable /{id}
    public ResponseEntity<HashMap<String, Object>> borrarJuego(@RequestParam("id") String idStr){  //Con rquestParam ?id=34
        HashMap<String, Object> rpta = new HashMap<>();
        try{
            System.out.println(idStr);
            int id = Integer.parseInt(idStr);

            Optional<Juegos> byId = juegosRepository.findById(id);
            if(byId.isPresent()){
                juegosRepository.deleteById(id);
                rpta.put("result","ok");
                rpta.put("msg","El juego ha sido eliminado");
            }else{
                rpta.put("result","error");
                rpta.put("msg","el ID ingresado no existe");
            }
            return ResponseEntity.ok(rpta);
        }catch (NumberFormatException e){
            rpta.put("result","error");
            rpta.put("msg","Debe ingresar un ID válido");
            return ResponseEntity.badRequest().body(rpta);
        }
    }

    //buscar por id
    @GetMapping("/buscar/{id}")
    public HashMap<String, Object> buscar(@PathVariable("id") int id) {
        HashMap<String, Object> respuesta = new HashMap<>();
        try {

            Optional<Juegos> byId = juegosRepository.findById(id);
            if (byId.isPresent()) {
                respuesta.put("resultado", "ok");
                respuesta.put("juego", byId.get());
                return respuesta;
            } else {
                respuesta.put("resultado de busqueda", "no existe");
                return respuesta;
            }
        } catch (NumberFormatException ex) {
            respuesta.put("error", "no es numero");
        }
        return respuesta;
    }

    //listar juegos por usuario

    @GetMapping("/juegosporusuario/{id}")
    public HashMap<String, Object> listarJuegosporUsuario(@PathVariable("id") int id) {
        HashMap<String, Object> respuesta = new HashMap<>();
        try {
            List<Object[]> byId = juegosxusuarioRepository.findJuegosByUsuarioId(id);
            if (!byId.isEmpty()) {
                respuesta.put("resultado", "ok");
                respuesta.put("juego", byId);
                return respuesta;
            } else {
                respuesta.put("resultado de busqueda", "no existe");
                return respuesta;
            }
        } catch (NumberFormatException ex) {
            respuesta.put("error", "no es numero");
        }
        return respuesta;
    }


}