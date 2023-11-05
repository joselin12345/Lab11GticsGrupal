package edu.pucp.gtics.lab11_gtics_20232.controller;
import edu.pucp.gtics.lab11_gtics_20232.entity.User;
import edu.pucp.gtics.lab11_gtics_20232.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {
    final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @GetMapping("/new")
    public String nuevoEmpleadoFrm(Model model, @ModelAttribute("user") User user) {
        return "user/nuevoUsuario";
    }

    @PostMapping("usuario/save")
    public String guardarusuario(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                                 RedirectAttributes attr, Model model) {

        if (bindingResult.hasErrors()) {
            return "/juegos/lista";
        } else {
                attr.addFlashAttribute("msg", "Usuario creado exitosamente");
                userRepository.save(user);
                return "redirect:/juegos/lista";

        }

    }
}
