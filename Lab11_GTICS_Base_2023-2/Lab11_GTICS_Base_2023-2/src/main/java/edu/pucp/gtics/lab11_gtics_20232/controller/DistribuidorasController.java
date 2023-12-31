package edu.pucp.gtics.lab11_gtics_20232.controller;

import edu.pucp.gtics.lab11_gtics_20232.dao.DistribuidoraDao;
import edu.pucp.gtics.lab11_gtics_20232.entity.Distribuidoras;
import edu.pucp.gtics.lab11_gtics_20232.entity.Paises;
import edu.pucp.gtics.lab11_gtics_20232.repository.DistribuidorasRepository;
import edu.pucp.gtics.lab11_gtics_20232.repository.PaisesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/distribuidoras")

public class DistribuidorasController {

    @Autowired
    DistribuidorasRepository distribuidorasRepository;

    @Autowired
    PaisesRepository paisesRepository;

    @Autowired
    DistribuidoraDao distribuidoraDao;

    @GetMapping(value = {"/lista"})
    public String listaDistribuidoras (Model model){
        model.addAttribute("listadistribuidoras", distribuidoraDao.lista());

        return "distribuidoras/lista";
    }

    @GetMapping("/nuevo")
    public String nuevaDistribuidora(Model model, @ModelAttribute("distribuidora") Distribuidoras distribuidora){
        List<Paises> listaPaises = paisesRepository.findAll();
        model.addAttribute("listaPaises", listaPaises);
        return "distribuidoras/editarFrm";
    }

    @PostMapping("/guardar")
    public String guardarDistribuidora(Model model, RedirectAttributes attr, @ModelAttribute("distribuidora") @Valid Distribuidoras distribuidora , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<Paises> listaPaises = paisesRepository.findAll();
            model.addAttribute("distribuidora", distribuidora);
            model.addAttribute("listaPaises", listaPaises);
            return "distribuidoras/editarFrm";
        } else {
            if (distribuidora.getIdDistribuidora() == 0) {
                attr.addFlashAttribute("msg", "Distribuidora creada exitosamente");
            } else {
                attr.addFlashAttribute("msg", "Distribuidora actualizada exitosamente");
            }
            distribuidoraDao.guardar(distribuidora);
            return "redirect:/distribuidoras/lista";
        }
    }

    @GetMapping("/editar")
    public String editarDistribuidoras( @ModelAttribute("distribuidora") Distribuidoras distribuidora, @RequestParam("id") int id, Model model){
        Distribuidoras distribuidoras = distribuidoraDao.buscarPorId(id);

        if (distribuidoras != null){
           distribuidora = distribuidoras;
            model.addAttribute("distribuidora", distribuidora);
            model.addAttribute("listaPaises", paisesRepository.findAll());
            return "distribuidoras/editarFrm";
        }else {
            return "redirect:/distribuidoras/lista";
        }

    }


    @GetMapping("/borrar")
    public String borrarDistribuidora(@RequestParam("id") int id, RedirectAttributes attr){

        distribuidoraDao.borrar(id);
        attr.addFlashAttribute("msg", "Juego borrado exitosamente");
        return "redirect:/distribuidoras/lista";
    }

}
