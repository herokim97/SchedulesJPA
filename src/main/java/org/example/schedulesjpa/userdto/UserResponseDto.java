package org.example.schedulesjpa.userdto;

import lombok.Getter;
import org.example.schedulesjpa.entity.User;

@Getter
public class UserResponseDto {

    private final String username;
    private final String email;

    public UserResponseDto(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public static UserResponseDto toUserDto(User user) {
        return new UserResponseDto(user.getUsername(), user.getEmail());
    }
}
