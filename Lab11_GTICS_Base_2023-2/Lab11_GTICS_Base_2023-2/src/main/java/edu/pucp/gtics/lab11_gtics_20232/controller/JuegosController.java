package edu.pucp.gtics.lab11_gtics_20232.controller;

import edu.pucp.gtics.lab11_gtics_20232.dao.DistribuidoraDao;
import edu.pucp.gtics.lab11_gtics_20232.dao.JuegoDao;
import edu.pucp.gtics.lab11_gtics_20232.entity.*;
import edu.pucp.gtics.lab11_gtics_20232.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller

public class JuegosController {

    @Autowired
    JuegosRepository juegosRepository;

    @Autowired
    PlataformasRepository plataformasRepository;

    @Autowired
    DistribuidorasRepository distribuidorasRepository;

    @Autowired
    GenerosRepository generosRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JuegoDao juegoDao;

    @Autowired
    DistribuidoraDao distribuidoraDao;

    @GetMapping(value = {"/juegos/lista"})
    public String listaJuegos (Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        User usuario = (User) session.getAttribute("usuario");
        System.out.println(usuario.getNombres());
        System.out.println(usuario.getIdusuario());
        model.addAttribute("lista",juegoDao.lista());
        return "juegos/lista";
    }

    @GetMapping(value = {"/juegos/xusuario"})
    public String listarJuegosXUsuario (Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        User usuario = (User) session.getAttribute("usuario");
        int idUsuario = usuario.getIdusuario();
        model.addAttribute("listaJuegosXUsuario",juegoDao.listarJuegosXUsuario(idUsuario));
        return "juegos/xusuario";
    }

    @GetMapping(value = {"", "/", "/vista"})
    public String vistaJuegos ( Model model){
        model.addAttribute("lista",juegoDao.lista());
        return "juegos/vista";
    }

    @GetMapping("/juegos/nuevo")
    public String nuevoJuegos(Model model, @ModelAttribute("juego") Juegos juego){
        List<Plataformas> listaPlataformas = plataformasRepository.findAll();
        List<Generos> listaGeneros = generosRepository.findAll();
        model.addAttribute("listaPlataformas", listaPlataformas);
        model.addAttribute("listaDistribuidoras", distribuidoraDao.lista());
        model.addAttribute("listaGeneros", listaGeneros);
        return "juegos/editarFrm";
    }

    @PostMapping("/juegos/guardar")
    public String guardarJuegos(Model model, RedirectAttributes attr, @ModelAttribute("juego") @Valid Juegos juego, BindingResult bindingResult ){
        if(bindingResult.hasErrors( )){
            List<Plataformas> listaPlataformas = plataformasRepository.findAll();
            List<Generos> listaGeneros = generosRepository.findAll();
            model.addAttribute("juego", juego);
            model.addAttribute("listaPlataformas", listaPlataformas);
            model.addAttribute("listaDistribuidoras", distribuidoraDao.lista());
            model.addAttribute("listaGeneros", listaGeneros);
            return "juegos/editarFrm";
        } else {
            if (juego.getIdJuego() == 0) {
                attr.addFlashAttribute("msg", "Juego creado exitosamente");
            } else {
                attr.addFlashAttribute("msg", "Juego actualizado exitosamente");
            }
            juegoDao.guardar(juego);
            return "redirect:/juegos/lista";
        }

    }

    @GetMapping("/juegos/editar")
    public String editarJuegos(@ModelAttribute("juego") Juegos juego, @RequestParam("id") int id, Model model){
        Juegos juegos1 = juegoDao.buscarPorId(id);

        if (juegos1 != null){
            juego = juegos1;
            List<Plataformas> listaPlataformas = plataformasRepository.findAll();
            List<Generos> listaGeneros = generosRepository.findAll();
            model.addAttribute("juego", juego);
            model.addAttribute("listaPlataformas", listaPlataformas);
            model.addAttribute("listaDistribuidoras", distribuidoraDao.lista());
            model.addAttribute("listaGeneros", listaGeneros);
            return "juegos/editarFrm";
        }else {
            return "redirect:/juegos/lista";
        }
    }



    @GetMapping("/juegos/borrar")
    public String borrarDistribuidora(@RequestParam("id") int id, RedirectAttributes attr){
        juegoDao.borrar(id);
        attr.addFlashAttribute("msg", "Juego borrado exitosamente");
        return "redirect:/juegos/lista";
    }








}
