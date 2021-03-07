package com.the.dark.side.crew.fejsbuk.auth.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
