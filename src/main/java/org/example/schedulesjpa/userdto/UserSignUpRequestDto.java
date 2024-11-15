package org.example.schedulesjpa.userdto;

import lombok.Getter;

@Getter
public class UserSignUpRequestDto {

    private final String username;
    private final String password;
    private final String email;

    public UserSignUpRequestDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
