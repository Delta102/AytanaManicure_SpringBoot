package com.example.AytanaManicure.Trabajador;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Entity
@Data
@Table(name = "TRABAJADOR", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Trabajador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long idUsuario;
    public String nombre;
    public String apellidos;
    public String tipo;
    public String email;
    public String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "idUsuario"), inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id"))
    private Collection<Rol> roles;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setId(Long id) {
        this.idUsuario = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellido(String apellido) {
        this.apellidos = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Rol> roles) {
        this.roles = roles;
    }

    public Trabajador(Long id, String nombre, String apellido, String email, String password, Collection<Rol> roles) {
        super();
        this.idUsuario = id;
        this.nombre = nombre;
        this.apellidos = apellido;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Trabajador(String nombre, String apellido, String email, String password, String tipo,
            Collection<Rol> roles) {
        super();
        this.nombre = nombre;
        this.apellidos = apellido;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.tipo = tipo;
    }

    public Trabajador() {

    }
}