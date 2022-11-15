package com.example.AytanaManicure.Cita;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitaService implements ICitaService {
    @Autowired
    private ICita data;

    @Override
    public void Guardar(Cita c) {
        data.save(c);
    }

    @Override
    public List<Cita> Listar() {
        return (List<Cita>) data.findAll();
    }

}
