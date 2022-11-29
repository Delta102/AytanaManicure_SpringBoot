package com.example.AytanaManicure.Cita;

import java.util.List;
import java.util.Optional;

public interface ICitaService {
    public List<Cita> Listar();

    public Optional<Cita> FindById(int id);
    public void Guardar(Cita c);

    public void Eliminar(int id);
}
