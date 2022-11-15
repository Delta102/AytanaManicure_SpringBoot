package com.example.AytanaManicure.Trabajador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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


    @GetMapping("/")
    public String Mostrar (){
        //List<Trabajador> trabajadores = service.Listar();
        //model.addAttribute("trabajadores", trabajadores);
        return carpeta+"listartrabajadores";
    }

    @PostMapping("/registrar")
    public String CrearTrabajador(@RequestParam("nombre") String nom, @RequestParam("apellidos") String ape,
            @RequestParam("email") String mail, @RequestParam("password") String password,
            @RequestParam("tipo") String tipo, Model model) {

        Trabajador t = new Trabajador();

        if (!service.ExisteEmail(mail)) {
            t.setNombre(nom);
            t.setApellidos(ape);

            t.setEmail(mail);
            t.setPassword(password);
            t.setTipo(tipo);
            service.Guardar(t);
        }

        return carpeta + "Index";
    }
}