package edu.pucp.gtics.lab11_gtics_20232.dao;

import edu.pucp.gtics.lab11_gtics_20232.entity.DistribuidoraDto;
import edu.pucp.gtics.lab11_gtics_20232.entity.Distribuidoras;
import edu.pucp.gtics.lab11_gtics_20232.entity.Juegos;
import edu.pucp.gtics.lab11_gtics_20232.entity.JuegosDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DistribuidoraDao {

    public List<Distribuidoras> lista(){
        List<Distribuidoras> lista = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        String endPoint = "http://localhost:8080/distribuidoras";
        ResponseEntity<Distribuidoras[]> responseEntity = restTemplate.getForEntity(endPoint,Distribuidoras[].class);
        if (responseEntity.getStatusCode().is2xxSuccessful()){
            Distribuidoras[] body = responseEntity.getBody();
            lista = Arrays.asList(body);
        }
        return lista;
    }


    public void guardar(Distribuidoras distribuidoras){
        RestTemplate restTemplate = new RestTemplate();
        String endPoint = "http://localhost:8080/distribuidoras";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Distribuidoras> httpEntity = new HttpEntity<>(distribuidoras, httpHeaders);
        if (distribuidoras.getIdDistribuidora() == 0){
            restTemplate.postForEntity(endPoint, httpEntity,Juegos.class);
        }else {
            restTemplate.put(endPoint, httpEntity,Juegos.class);
        }

    }

    public Distribuidoras buscarPorId(int id){
        Distribuidoras distribuidoras = null;
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/distribuidoras/buscar/" + id;
        ResponseEntity<DistribuidoraDto> forEntity = restTemplate.getForEntity(url, DistribuidoraDto.class);

        if (forEntity.getStatusCode().is2xxSuccessful()){
            DistribuidoraDto distribuidoraDto = forEntity.getBody();
            distribuidoras = distribuidoraDto.getDistribuidora();
        }
        return distribuidoras;
    }

    public void borrar(int id){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("http://localhost:8080/distribuidoras/borrar?id="+id);
    }

}
