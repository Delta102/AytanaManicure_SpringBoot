package com.example.AytanaManicure.Trabajador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/trabajador/")
@Controller
public class ControladorTrabajador {
    
    String carpeta = "Trabajador/";

    @Autowired
    private ITrabajadorService service;

    @GetMapping("/registro")
    public String Nuevo()
    {

        return carpeta + "registro"; // nuevo.html
    }

    @ModelAttribute("trabajador")
    public TrabajadorRegistroDTO retornarNuevoRegistroUsuarioDTO() {
        return new TrabajadorRegistroDTO();
    }


    @PostMapping("registrar")
    public String CrearTrabajador(@ModelAttribute("trabajador") TrabajadorRegistroDTO registroDTO) {
        service.Guardar(registroDTO);

        return "redirect:/trabajador/registro?exito";
    }

    @GetMapping("login")
    public String iniciarSesion() {
        return carpeta + "login";
    }

    @GetMapping("/perfil")
    public String miPerfil(@RequestParam("id") String mail, Model model) {
        var trabajador = service.buscarByEmail(mail);
        model.addAttribute("nombre", trabajador.getNombre());
        model.addAttribute("apellido", trabajador.getApellidos());
        model.addAttribute("id", trabajador.getIdUsuario());
        model.addAttribute("tipo", trabajador.getTipo());

        return carpeta + "Perfil";
    }

}