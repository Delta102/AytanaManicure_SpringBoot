package com.example.AytanaManicure.Trabajador;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITrabajador extends CrudRepository<Trabajador, Integer> {
    @Query(value="SELECT * FROM trabajador" + "WHERE nombre LIKE %1%?", nativeQuery = true)
    List<Trabajador> buscarPorTodo(String dato);

    @Query(value="SELECT * FROM trabajador "
            + "ORDER BY nombre ASC",nativeQuery=true)
    List<Trabajador> OrderAsc();
    
    @Query(value="SELECT * FROM trabajador "
            + "ORDER BY nombre DESC",nativeQuery=true)
    List<Trabajador> OrderDesc();

    @Query(value = "SELECT email FROM trabajador" + "WHERE email LIKE usuario@usuario.pe", nativeQuery = true)
    String buscarEmail(String email);
}