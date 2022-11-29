package com.example.AytanaManicure.Servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioService implements IServicioService {

    @Autowired
    private IServicio data;

    @Override
    public void Guardar(Servicio s) {
        data.save(s);
    }

    @Override
    public List<Servicio> ListarServicios() {
        return (List<Servicio>) data.findAll();
    }

    public Optional<Servicio> FindById(int id) {
        return data.findById(id);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }
}