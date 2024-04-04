package com.test.backendapirest.model.dao;

import com.test.backendapirest.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioDao extends CrudRepository <Usuario, Long> {

    Usuario findByEmail (String email);

    Integer deleteByEmail(String email);


}
