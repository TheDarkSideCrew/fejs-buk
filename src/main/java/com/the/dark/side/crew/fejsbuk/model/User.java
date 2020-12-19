package com.the.dark.side.crew.fejsbuk.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @NotBlank(message = "First name is mandatory")
//    @Column(name = "first_name")
    private String firstName;

//    @NotBlank(message = "Last name is mandatory")
//    @Column(name = "last_name")
    private String lastName;

//    @NotBlank(message = "Login is mandatory")
//    @Size(min = 6, max = 32, message = "Login must be between 6 and 32 characters")
    private String login;

//    @NotBlank(message = "Email is mandatory")
//    @Email(message = "Email should be valid")
    private String email;

    public User(String firstName, String lastName, String login, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.email = email;
    }
}
