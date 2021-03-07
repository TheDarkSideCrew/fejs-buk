package com.the.dark.side.crew.fejsbuk.auth.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String login;

    private String password;

    @Column(unique = true)
    private String email;

    private String firstName;

    private String lastName;

}
