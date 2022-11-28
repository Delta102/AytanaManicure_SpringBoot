package com.example.AytanaManicure.Trabajador;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TrabajadorService implements ITrabajadorService{

    @Autowired
    private ITrabajador data;

    /*
     * @Autowired
     * private TrabajadorRepositorio userRepository;
     */
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Trabajador Guardar(TrabajadorRegistroDTO registroDTO) {
        Trabajador usuario = new Trabajador(registroDTO.getNombre(),
                registroDTO.getApellido(), registroDTO.getEmail(),
                passwordEncoder.encode(registroDTO.getPassword()), registroDTO.getTipo(),
                Arrays.asList(new Rol("ROLE_USER")));
        return data.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Trabajador usuario = data.buscarEmail(username);

        if (usuario == null)
            throw new UsernameNotFoundException("Usuario o contraseña incorrecta");

        return new User(usuario.getEmail(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
    }

    @Override
    public List<Trabajador> Listar() {
        return (List<Trabajador>) data.findAll();
    }

    @Override
    public Optional<Trabajador> ConsultarId(Long id) {
        return data.findById(id);
    }



    @Override
    public void Eliminar(Long id) {
        data.deleteById(id);
    }

    @Override
    public List<Trabajador> Buscar(String dato) {
        return data.buscarPorTodo(dato);
    }

    @Override
    public List<Trabajador> OrdenAscendente() {
        return data.OrderAsc();
    }

    @Override
    public List<Trabajador> OrdenDescendente() {
        return data.OrderDesc();
    }
}
