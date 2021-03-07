package com.the.dark.side.crew.fejsbuk.auth.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String login;
    private String email;
    private String firstName;
    private String lastName;
    @JsonIgnore
    private String password;
}
