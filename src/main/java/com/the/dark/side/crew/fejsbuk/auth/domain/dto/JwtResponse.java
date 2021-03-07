package com.the.dark.side.crew.fejsbuk.auth.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

    private String tokenBearer;
}
