package com.test.backendapirest.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name="usuarios")
@Getter
@Setter
@ToString
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @NonNull
    @Column(unique = true)
    private String email;
    @Column
    private String password;
    @Column(name = "create_date")
    private Date create;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "citycode", column = @Column(name = "citycode")),
            @AttributeOverride(name = "number", column = @Column(name = "number")),
            @AttributeOverride(name = "contrycode", column = @Column(name = "contrycode"))
    })
    private List<Phones> phones = new ArrayList<>();

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date modified;

    @Column(name = "last_login")
    @Temporal(TemporalType.DATE)
    private Date lastlogin;

    @Column
    private String token;

    @Column
    private int isactive;

    @Transient
    private boolean isNew;
    @Column
    private Integer number;
    @Column
    private Integer citycode;
    @Column
    private Integer contrycode;

}
