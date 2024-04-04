package com.test.backendapirest.model.dto;

import com.test.backendapirest.model.entity.Phones;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
@ToString
@Getter
@Setter
public class UsuarioDtoRequest implements Serializable {

    private String name;

    private String email;

    private String password;

    private int isactive;

    private List<Phones> phones;

}
