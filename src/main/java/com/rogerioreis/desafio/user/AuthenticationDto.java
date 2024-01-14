package com.rogerioreis.desafio.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDto {

    private String login;

    private String password;

    public AuthenticationDto(User user){
        this.login = user.getLogin();
        this.password = user.getPassword();
    }

}
