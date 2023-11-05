package edu.pucp.gtics.lab11_gtics_20232.dao;

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
public class JuegoDao {

    public List<Juegos> lista(){
        List<Juegos> lista = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        String endPoint = "http://localhost:8080/juegos";
        ResponseEntity<Juegos[]> responseEntity = restTemplate.getForEntity(endPoint,Juegos[].class);
        if (responseEntity.getStatusCode().is2xxSuccessful()){
            Juegos[] body = responseEntity.getBody();
            lista = Arrays.asList(body);
        }
        return lista;
    }

    public void guardar(Juegos juegos){
        RestTemplate restTemplate = new RestTemplate();
        String endPoint = "http://localhost:8080/";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Juegos> httpEntity = new HttpEntity<>(juegos, httpHeaders);
        if (juegos.getIdjuego() == 0){
            restTemplate.postForEntity(endPoint, httpEntity,Juegos.class);
        }else {
            restTemplate.put(endPoint, httpEntity,Juegos.class);
        }

    }
    public Juegos buscarPorId(int id){
        Juegos juegos = null;
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/juegos/" + id;
        ResponseEntity<JuegosDto> forEntity = restTemplate.getForEntity(url, JuegosDto.class);

        if (forEntity.getStatusCode().is2xxSuccessful()){
            JuegosDto juegosDto = forEntity.getBody();
            juegos = juegosDto.getJuegos();
        }
        return juegos;
    }

    public void borrar(int id){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("http://localhost:8080/juegos?id"+id);
    }

}
