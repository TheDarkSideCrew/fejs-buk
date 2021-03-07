package com.the.dark.side.crew.fejsbuk.auth.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String login;
    private String email;
    private String firstName;
    private String lastName;
    @JsonIgnore
    private String password;
}
