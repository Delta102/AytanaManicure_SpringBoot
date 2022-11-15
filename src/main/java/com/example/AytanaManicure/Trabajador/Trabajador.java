package com.example.AytanaManicure.Trabajador;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "TRABAJADOR")
public class Trabajador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int idUsuario;
    public String nombre;
    public String apellidos;
    public String tipo;
    public String email;
    public String password;
}