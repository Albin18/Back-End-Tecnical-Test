package com.test.backendapirest.model.service;

import com.test.backendapirest.model.dto.UsuarioDtoRequest;
import com.test.backendapirest.model.dto.UsuarioDtoResponse;
import com.test.backendapirest.model.entity.Usuario;

import java.util.List;
public interface IUsuarioService {

    public List<Usuario> findAll();

    public Usuario findByEmail(String email);

    public UsuarioDtoResponse save(UsuarioDtoRequest usuariodt);

    public UsuarioDtoResponse update(Usuario usuario, UsuarioDtoRequest usuariodt);

    public Integer delete (String email);

    public UsuarioDtoResponse serializar(Usuario usuario);
}
