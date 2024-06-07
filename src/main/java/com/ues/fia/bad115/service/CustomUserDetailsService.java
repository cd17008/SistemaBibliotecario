package com.ues.fia.bad115.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.ues.fia.bad115.clase.Usuario_Rol;
import com.ues.fia.bad115.repository.UsuarioRolRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import com.ues.fia.bad115.repository.UsuarioRepository;
import com.ues.fia.bad115.clase.Usuario;
import java.util.Collections;

import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRolRepository userRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario_Rol user = userRepository.findByIdusuario(Integer.parseInt(username));
        Usuario usuario = usuarioRepository.findById(Integer.parseInt(username));
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRol().getRol().toUpperCase());
        Set<GrantedAuthority> authorities = Collections.singleton(authority);

        return new org.springframework.security.core.userdetails.User(
                String.valueOf(user.getIdusuario()), usuario.getPassword(), authorities);
    }
}
