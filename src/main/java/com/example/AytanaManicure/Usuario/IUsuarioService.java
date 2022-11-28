package com.example.AytanaManicure.Usuario;

import java.util.Optional;


public interface IUsuarioService {
    public void Guardar(Usuario u);

    public Optional<Usuario> FindById(int id);
}