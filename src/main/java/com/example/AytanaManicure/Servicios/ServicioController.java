package com.example.AytanaManicure.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/service/")
@Controller
public class ServicioController {
    String carpeta = "Servicios/";

    @Autowired
    private IServicioService service;

    @GetMapping("listaServicios")
    public String listaServicios(Model model) {

        List<Servicio> servicios = service.ListarServicios();

        model.addAttribute("servicios", servicios);

        return carpeta + "ListaServicios";
    }

    @GetMapping("registroServicio")
    public String registro() {
        return carpeta + "CrearServicio";
    }

    @PostMapping("/registrarServicio")
    public String registrar(@RequestParam("nService") String nombre, @RequestParam("pService") float precio,
            ModelMap model) {

        Servicio s = new Servicio();

        s.setNombreServicio(nombre);
        s.setPrecioServicio(precio);

        service.Guardar(s);
        return " ";
    }
}
