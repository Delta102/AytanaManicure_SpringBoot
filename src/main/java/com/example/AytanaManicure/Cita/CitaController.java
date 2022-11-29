package com.example.AytanaManicure.Cita;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.AytanaManicure.Servicios.IServicioService;
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
    @Autowired
    private IServicioService service;

    @GetMapping("registrarUsuario")
    public String paraRegistro() {
        return carpeta + "AgendarUsuario";
    }

    @PostMapping("/registroUsuario")
    public String CrearUsuario(@RequestParam("nombreUsuario") String nombre,
            @RequestParam("apellidoUsuario") String apellido, Model model) {

        Usuario u = new Usuario();

        u.setNombre(nombre);
        u.setApellido(apellido);

        userService.Guardar(u);

        var servicios = service.ListarServicios();

        model.addAttribute("usuario", u.idUsuario);
        model.addAttribute("servicios", servicios);

        return carpeta + "CrearCita";
    }



    @PostMapping("/agendar")
    public String AgendarCita(@RequestParam("fecha") String fromDate, @RequestParam("hora") String fromTime,
            @RequestParam("trabajador") String trabajador, @RequestParam("estado") String estado,
            @RequestParam("usuario") int id, @RequestParam("proceso") int idProceso, Model model)
            throws ParseException {

        Cita c = new Cita();

        // var date = new SimpleDateFormat("dd/MM/yyyy").parse(fromDate);

        var fechaActual = LocalDateTime.now();

        var fechaTotal = fechaActual.getYear() + "-" + fechaActual.getMonthValue() + "-" + fechaActual.getDayOfMonth();

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy");

        Date date = format.parse(fromDate);
        Date dateTemp = format.parse(fechaTotal);

        if (date.before(dateTemp)) {
            model.addAttribute("fecha", "Fecha no válida, por favor, vuelva a la página de inico e inténtelo de nuevo");
            return carpeta + "CrearCita";
        }
        c.setFechaCita(fromDate);
        c.setHoraCita(fromTime);
        c.setEstadoCita(estado);
        c.setNombreTrabajador(trabajador);
        Optional<Usuario> user = userService.FindById(id);
        var proceso = service.FindById(idProceso);

        c.setProceso(proceso.get());
        c.setUsuario(user.get());
        citaService.Guardar(c);

        return "Index";
    }

    @GetMapping("/listaCitas")
    public String ListarCitas(Model model) {

        String estado = "SIN TRABAJADOR";

        List<Cita> citas = citaService.Listar();
        List<Cita> listaCita = new ArrayList<Cita>();

        for (int i = 0; i < citas.size(); i++) {
            if (citas.get(i).nombreTrabajador.equals(estado))
                listaCita.add(citas.get(i));
        }

        model.addAttribute("listaCitas", listaCita);
        return carpeta + "ListaCitas";

    }

    @GetMapping("/editarCitaTrabajador")
    public String actualizarCitaTrabajador(@RequestParam("mail") String mail, @RequestParam("id") int idCita,
            Model model) {

        var tempCita = citaService.FindById(idCita);

        var cita = tempCita.get();

        model.addAttribute("mail", mail);
        model.addAttribute("cita", cita);

        return carpeta + "ActualizarCita";
    }

    @GetMapping("/editarEstadoCita")
    public String actualizarCitaEstado(@RequestParam("id") int idCita,
            Model model) {

        var tempCita = citaService.FindById(idCita);

        var cita = tempCita.get();

        model.addAttribute("cita", cita);

        return carpeta + "ActualizarCita";
    }

    @PostMapping("editarTrabajador")
    public String actualizarTrabajador(@RequestParam("actualizar") String nom,
            @RequestParam("idCita") int id,
            Model model) {

        var tempCita = citaService.FindById(id);
        var cita = tempCita.get();

        if (cita.getNombreTrabajador().equals("SIN TRABAJADOR")) {
            cita.setNombreTrabajador(nom);
            citaService.Guardar(cita);
        }
        if (cita.getEstadoCita().equals("SIN ACTIVAR") && nom.equals("ACTIVADO")) {
            cita.setEstadoCita(nom);
            citaService.Guardar(cita);
        }

        if (!cita.getNombreTrabajador().equals("SIN TRABAJADOR"))
            return ListarCitasByUser(cita.getNombreTrabajador(), model);

        if (cita.getNombreTrabajador().equals("SIN TRABAJADOR"))
            return ListarCitas(model);

        return ListarCitas(model);
    }

    @GetMapping("/listaCitasbyUserMail")
    public String ListarCitasByUser(@RequestParam("mail") String mail, Model model) {

        String estado = mail;
        List<Cita> citas = citaService.Listar();
        List<Cita> listaCita = new ArrayList<Cita>();

        for (int i = 0; i < citas.size(); i++) {
            if (citas.get(i).nombreTrabajador.equals(estado))
                listaCita.add(citas.get(i));
        }

        model.addAttribute("listaCitas", listaCita);
        return carpeta + "ListaCitas";
    }

    @GetMapping("listaCitasPasadasbyUserMail")
    public String ListarCitasAtendidasByUser(@RequestParam("mail") String mail,
            Model model) throws ParseException {

        String estado = mail;
        List<Cita> citas = citaService.Listar();
        List<Cita> listaCita = new ArrayList<Cita>();

        var fechaActual = LocalDateTime.now();

        var fechaTotal = fechaActual.getYear() + "-" + fechaActual.getMonthValue() + "-" + fechaActual.getDayOfMonth()
                + " " + fechaActual.getHour() + ":" + fechaActual.getMinute();

        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("YYYY-MM-dd hh:mm");

        Date dateTemp = format2.parse(fechaTotal);
        Date dateCita = new Date();

        for (int i = 0; i < citas.size(); i++) {
            if (citas.get(i).fechaCita == null || citas.get(i).horaCita == null) {
                return "Error";
            } else {
                var dateTimeCita = citas.get(i).horaCita;

                String[] HoraMinArray = dateTimeCita.split(":");
                int valorHora = Integer.parseInt(HoraMinArray[0]); // Hora
                int valorMinutos = Integer.parseInt(HoraMinArray[1]); // Minutos.

                var dateString = citas.get(i).fechaCita;
                dateCita = format.parse(dateString);

                dateCita.setHours(valorHora);
                dateCita.setMinutes(valorMinutos);

                System.out.println("DateCita: " + dateCita + "DateTemp: " + dateTemp);

                if (citas.get(i).nombreTrabajador.equals(estado) && dateCita.before(dateTemp))
                    listaCita.add(citas.get(i));

                valorHora = 0;
                valorMinutos = 0;

                dateString = null;
                dateCita = null;
            }
        }

        model.addAttribute("estado", 1);
        model.addAttribute("listaCitas", listaCita);
        return carpeta + "ListaCitas";
    }

    @GetMapping("eliminar")
    public String Eliminar(@RequestParam("id") int id, Model model) {

        var cita = citaService.FindById(id);

        var mail = cita.get().getNombreTrabajador();

        citaService.Eliminar(id);
        return ListarCitasByUser(mail, model);
    }
}