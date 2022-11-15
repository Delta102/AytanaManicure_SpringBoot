package com.example.AytanaManicure.Trabajador;

import java.util.List;
import java.util.Optional;


public interface ITrabajadorService {
    public List<Trabajador> Listar();
    public Optional<Trabajador> ConsultarId(int id);
    public void Guardar(Trabajador p);
    public void Eliminar(int id);

    public Boolean ExisteEmail(String email);

    public List<Trabajador> Buscar(String dato);
    public List<Trabajador> OrdenAscendente();
    public List<Trabajador> OrdenDescendente();
}
