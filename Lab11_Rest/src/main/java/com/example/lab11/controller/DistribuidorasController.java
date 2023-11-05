package com.example.lab11.controller;

import com.example.lab11.entity.Distribuidoras;
import com.example.lab11.entity.Juegos;
import com.example.lab11.repository.DistribuidorasRepository;
import com.example.lab11.repository.JuegosRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/distribuidoras")
public class DistribuidorasController {

    final JuegosRepository juegosRepository;
    final DistribuidorasRepository distribuidorasRepository;
    public DistribuidorasController(JuegosRepository juegosRepository, DistribuidorasRepository distribuidorasRepository) {
        this.juegosRepository = juegosRepository;
        this.distribuidorasRepository = distribuidorasRepository;
    }


    @GetMapping(value = "")
    public List<Distribuidoras> listarDistribuidoras() {
        return distribuidorasRepository.findAll();
    }


    /*
    No sale todavía esto, el código de stuardo no funca
    @GetMapping(value = "")
    public List<Distribuidoras> listarDistribuidoras() {
        // Configurar RestTemplate con autenticación básica
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(
                new BasicAuthenticationInterceptor("oscar.diaz@gmail.com", "oscar.diaz")
        );

        // Realizar la solicitud HTTP a la URL protegida
        ResponseEntity<Distribuidoras[]> response = restTemplate.getForEntity(
                "http://localhost:8080/distribuidoras", Distribuidoras[].class
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            return Arrays.asList(response.getBody());
        } else {
            // Manejar errores aquí si es necesario
            return Collections.emptyList();
        } aun falta probar esto
    }
    */
    @PostMapping(value = {"", "/guardar"})
    public ResponseEntity<HashMap<String, Object>> crearDistribuidora(
            @RequestBody Distribuidoras distribuidora,   //para crear se usa body en postman - raw
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {  //pa que devuelva o no id: ...8080/product?fetchid=true

        HashMap<String, Object> responseJson = new HashMap<>();

        distribuidorasRepository.save(distribuidora);
        if (fetchId) {
            responseJson.put("id", distribuidora.getIdDistribuidora());
        }
        responseJson.put("estado", "creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseJson);
    }


    @PutMapping(value = {"","/actualizar"})
    public ResponseEntity<HashMap<String, Object>> actualizarDistribuidora(@RequestBody Distribuidoras distribuidoraRecibida) {

        HashMap<String, Object> rpta = new HashMap<>();

        if (distribuidoraRecibida.getIdDistribuidora() != null && distribuidoraRecibida.getIdDistribuidora() > 0) {

            Optional<Distribuidoras> byId = distribuidorasRepository.findById(distribuidoraRecibida.getIdDistribuidora());
            if (byId.isPresent()) {
                Distribuidoras distribuidoraActualizada = byId.get();

                if (distribuidoraRecibida.getNombre() != null)
                    distribuidoraActualizada.setNombre(distribuidoraRecibida.getNombre());

                if (distribuidoraRecibida.getDescripcion() != null)
                    distribuidoraActualizada.setDescripcion(distribuidoraRecibida.getDescripcion());

                if (distribuidoraRecibida.getFundacion() != null)
                    distribuidoraActualizada.setFundacion(distribuidoraRecibida.getFundacion());

                if (distribuidoraRecibida.getWeb() != null)
                    distribuidoraActualizada.setWeb(distribuidoraRecibida.getWeb());

                if (distribuidoraRecibida.getSede() != null)
                    distribuidoraActualizada.setSede(distribuidoraRecibida.getSede());
                distribuidorasRepository.save(distribuidoraActualizada);
                rpta.put("resultado", "ok");
                rpta.put("msg", "La distribuidora ha sido actualizada");
                return ResponseEntity.ok(rpta);
            } else {
                rpta.put("resultado", "error");
                rpta.put("msg", "El ID de la distribuidora ingresada no existe");
                return ResponseEntity.badRequest().body(rpta);
            }
        } else {
            rpta.put("resultado", "error");
            rpta.put("msg", "Debe ingresar una distribuidora con ID");
            return ResponseEntity.badRequest().body(rpta);
        }
    }


    @DeleteMapping("/borrar")                                                                           //con PathVariable /{id}
    public ResponseEntity<HashMap<String, Object>> borrarDistribuidora(@RequestParam("id") String idStr){  //Con rquestParam ?id=34
        HashMap<String, Object> rpta = new HashMap<>();
        try{
            System.out.println(idStr);
            int id = Integer.parseInt(idStr);

            Optional<Distribuidoras> byId = distribuidorasRepository.findById(id);
            if(byId.isPresent()){
                distribuidorasRepository.deleteById(id);
                rpta.put("result","ok");
                rpta.put("msg","La distribuidora ha sido eliminada");
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

    @GetMapping("/buscar/{id}")
    public HashMap<String, Object> buscar(@PathVariable("id") int id) {
        HashMap<String, Object> respuesta = new HashMap<>();
        try {

            Optional<Distribuidoras> byId = distribuidorasRepository.findById(id);
            if (byId.isPresent()) {
                respuesta.put("resultado", "ok");
                respuesta.put("distribuidora", byId.get());
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
