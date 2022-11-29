package com.example.AytanaManicure.Usuario;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuario data;

    @Override
    public void Guardar(Usuario u) {
        data.save(u);
    }

    @Override
    public Optional<Usuario> FindById(int id) {
        return data.findById(id);
    }

    @Override
    public List<Usuario> Listar() {
        return (List<Usuario>) data.findAll();
    }

}