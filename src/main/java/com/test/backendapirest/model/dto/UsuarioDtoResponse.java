package com.test.backendapirest.model.dto;

import com.test.backendapirest.model.entity.Phones;
import jakarta.persistence.Column;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class UsuarioDtoResponse implements Serializable {

    private UUID id;
    private String name;

    private String email;

    private String password;
    private List<Phones> phones = new ArrayList<>();
    private Date create;
    private Date modified;
    private Date lastlogin;
    private String token;

    private int isactive;

    public UsuarioDtoResponse() {

    }
}
