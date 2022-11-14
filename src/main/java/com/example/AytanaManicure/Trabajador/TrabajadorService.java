package com.example.AytanaManicure.Trabajador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrabajadorService implements ITrabajadorService{

    @Autowired
    private ITrabajador data;

    @Override
    public List<Trabajador> Listar() {
        return (List<Trabajador>) data.findAll();
    }

    @Override
    public Optional<Trabajador> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Trabajador p) {
        data.save(p);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<Trabajador> Buscar(String dato) {
        return data.buscarPorTodo(dato);
    }

    @Override
    public List<Trabajador> OrdenAscendente() {
        return data.OrderAsc();
    }

    @Override
    public List<Trabajador> OrdenDescendente() {
        return data.OrderDesc();
    }
    
}
