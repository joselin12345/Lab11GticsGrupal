package edu.pucp.gtics.lab11_gtics_20232.dao;

import edu.pucp.gtics.lab11_gtics_20232.entity.Juegos;
import edu.pucp.gtics.lab11_gtics_20232.entity.JuegosDto;
import edu.pucp.gtics.lab11_gtics_20232.entity.JuegosxUsuario;
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


    public List<JuegosxUsuario> listarJuegosXUsuario(int idUsuario){
        List<JuegosxUsuario> listaJuegosXUsuario = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        String endPoint = "http://localhost:8080/juegos"; //falta modificar estoo de acuerdo a lo deMASSIEL
        ResponseEntity<JuegosxUsuario[]> responseEntity = restTemplate.getForEntity(endPoint,JuegosxUsuario[].class);
        if (responseEntity.getStatusCode().is2xxSuccessful()){
            JuegosxUsuario[] body = responseEntity.getBody();
            listaJuegosXUsuario = Arrays.asList(body);
        }
        return listaJuegosXUsuario;
    }


    public void guardar(Juegos juegos){
        RestTemplate restTemplate = new RestTemplate();
        String endPoint = "http://localhost:8080/juegos/guardar";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Juegos> httpEntity = new HttpEntity<>(juegos, httpHeaders);
        if (juegos.getIdJuego() == 0){
            restTemplate.postForEntity(endPoint, httpEntity,Juegos.class);
        }else {
            restTemplate.put(endPoint, httpEntity,Juegos.class);
        }

    }

    public Juegos buscarPorId(int id){
        Juegos juegos = null;
        RestTemplate restTemplate = new RestTemplate();
        // agregar la direecion de buscar por id del juego
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
        restTemplate.delete("http://localhost:8080/juegos/borrar?id="+id);
    }



}
