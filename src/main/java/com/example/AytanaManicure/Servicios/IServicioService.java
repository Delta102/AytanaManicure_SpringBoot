package com.example.AytanaManicure.Servicios;

import java.util.List;
import java.util.Optional;

public interface IServicioService {

    public List<Servicio> ListarServicios();

    public Optional<Servicio> FindById(int id);

    public void Eliminar(int id);

    public void Guardar(Servicio s);
}