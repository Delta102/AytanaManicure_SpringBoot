package com.example.AytanaManicure.Cita;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.AytanaManicure.Usuario.Usuario;

import lombok.Data;

@Entity
@Data
@Table(name = "CITA")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int idCita;

    public String fechaCita;
    public String horaCita;

    public String nombreTrabajador;
    public String estadoCita;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}