package com.example.AytanaManicure.Usuario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IUsuario extends CrudRepository<Usuario, Integer> {

    @Query(value = "SELECT * FROM usuario" + "WHERE id_usuario LIKE %1%?", nativeQuery = true)
    Usuario buscarPorTodo(int id);
}