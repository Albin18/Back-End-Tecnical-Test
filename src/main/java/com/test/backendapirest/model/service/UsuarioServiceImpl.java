package com.test.backendapirest.model.service;

import com.test.backendapirest.model.dao.IUsuarioDao;
import com.test.backendapirest.model.dto.UsuarioDtoRequest;
import com.test.backendapirest.model.dto.UsuarioDtoResponse;
import com.test.backendapirest.model.entity.Phones;
import com.test.backendapirest.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
    @Autowired
    private IUsuarioDao usuarioDao;
    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioDao.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario findByEmail(String email){
        return usuarioDao.findByEmail(email);
    }
    @Override
    @Transactional
    public UsuarioDtoResponse save(UsuarioDtoRequest usuariodt) {
        Usuario usuario = new Usuario();
        Date create = new Date();
        UUID uuid = UUID.randomUUID();
        usuario.setNew(true);
        usuario.setName(usuariodt.getName());
        usuario.setPassword(usuariodt.getPassword());
        usuario.setEmail(usuariodt.getEmail());
        usuario.setToken(uuid.toString());
        usuario.setIsactive(1);
        usuario.setCreate(create);
        usuario.setModified(create);
        usuario.setLastlogin(create);
        usuariodt.getPhones().stream().forEach(phones -> {
            usuario.setContrycode(phones.getContrycode());
            usuario.setNumber(phones.getNumber());
            usuario.setCitycode(phones.getCitycode());
        });
        var usuariodao = usuarioDao.save(usuario);
        System.out.println("usuario retornado: " + usuariodao);
        return serializar(usuariodao);
    }

    @Override
    public UsuarioDtoResponse serializar(Usuario usuario){

        usuario.getPhones().add(new Phones(usuario.getNumber(), usuario.getCitycode(), usuario.getContrycode()));
        return new UsuarioDtoResponse().builder()
                .id(usuario.getId())
                .name(usuario.getName())
                .email(usuario.getEmail())
                .password(usuario.getPassword())
                .phones(usuario.getPhones())
                .create(usuario.getCreate())
                .modified(usuario.getModified())
                .lastlogin(usuario.getLastlogin())
                .token(usuario.getToken())
                .isactive(usuario.getIsactive())
                .build();
    }

    @Override
    public UsuarioDtoResponse update(Usuario usuario, UsuarioDtoRequest usuariodt) {
        System.out.println("usuario = " + usuario);
        Date modifiedDate = new Date();
        usuario.setNew(false);
        usuario.setName(usuariodt.getName());
        usuario.setPassword(usuariodt.getPassword());
        usuario.setModified(modifiedDate);
        usuario.setIsactive(usuariodt.getIsactive());
        usuario.setPhones(new ArrayList<>());
        System.out.println("usuario antes del for = " + usuariodt);
        usuariodt.getPhones().stream().forEach( phones -> {
            usuario.setCitycode(phones.getCitycode());
            usuario.setNumber(phones.getNumber());
            usuario.setContrycode(phones.getContrycode());
        });

        return this.serializar(usuarioDao.save(usuario));
    }


    @Override
    @Transactional
    public Integer delete(String email) {
        return usuarioDao.deleteByEmail(email);
    }
}
