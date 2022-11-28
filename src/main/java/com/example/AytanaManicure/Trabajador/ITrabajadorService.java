package com.example.AytanaManicure.Trabajador;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface ITrabajadorService extends UserDetailsService {
    public List<Trabajador> Listar();

    public Optional<Trabajador> ConsultarId(Long id);

    public Trabajador Guardar(TrabajadorRegistroDTO p);

    public void Eliminar(Long id);

    public List<Trabajador> Buscar(String dato);
    public List<Trabajador> OrdenAscendente();
    public List<Trabajador> OrdenDescendente();
}
