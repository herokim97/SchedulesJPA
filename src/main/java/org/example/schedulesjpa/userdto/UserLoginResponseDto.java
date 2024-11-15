package org.example.schedulesjpa.userdto;

import lombok.Getter;

@Getter
public class UserLoginResponseDto {
    private final String username;
    private final String email;

    public UserLoginResponseDto(String username, String email ) {
        this.username = username;
        this.email = email;
    }
}
