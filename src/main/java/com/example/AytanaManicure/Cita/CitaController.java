package com.example.AytanaManicure.Cita;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.AytanaManicure.Usuario.IUsuarioService;
import com.example.AytanaManicure.Usuario.Usuario;



@RequestMapping("/cita/")
@Controller
public class CitaController {

    String carpeta = "Cita/";

    @Autowired
    private ICitaService citaService;
    @Autowired
    private IUsuarioService userService;

    @GetMapping("/registrarUsuario")
    public String paraRegistro() {
        return carpeta + "AgendarUsuario";
    }

    @PostMapping("/agendarUsuario")
    public String CrearUsuario(@RequestParam("nombreUsuario") String nombre,
            @RequestParam("apellidoUsuario") String apellido, Model model) {

        Usuario u = new Usuario();

        u.setNombre(nombre);
        u.setApellido(apellido);

        userService.Guardar(u);

        model.addAttribute("usuario", u.idUsuario);

        return carpeta + "CrearCita";
    }

    // LISTA DE COLABORADORES: YO

    @PostMapping("/agendar")
    public String AgendarCita(@RequestParam("fecha") String fromDate, @RequestParam("hora") String fromTime,
            @RequestParam("trabajador") String trabajador, @RequestParam("estado") String estado,
            @RequestParam("usuario") int id, ModelMap model) throws ParseException {

        Cita c = new Cita();

        // var date = new SimpleDateFormat("dd/MM/yyyy").parse(fromDate);

        c.setFechaCita(fromDate);
        c.setHoraCita(fromTime);
        c.setEstadoCita(estado);
        c.setNombreTrabajador(trabajador);
        Optional<Usuario> user = userService.FindById(id);
        c.setUsuario(user.get());
        citaService.Guardar(c);

        return "../Index";
    }

    @GetMapping("/listaCitas")
    public String ListarCitas(Model model) {

        List<Cita> citas = citaService.Listar();
        model.addAttribute("citas", citas);

        return carpeta + "ListaCitas";
    }
}
