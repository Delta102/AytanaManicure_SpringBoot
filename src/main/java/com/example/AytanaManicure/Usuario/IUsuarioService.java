package com.example.AytanaManicure.Usuario;

import java.util.List;
import java.util.Optional;


public interface IUsuarioService {
    public void Guardar(Usuario u);

    public List<Usuario> Listar();
    public Optional<Usuario> FindById(int id);
}