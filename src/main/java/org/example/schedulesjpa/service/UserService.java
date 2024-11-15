package org.example.schedulesjpa.service;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.schedulesjpa.entity.User;
import org.example.schedulesjpa.repository.UserRepository;
import org.example.schedulesjpa.userdto.UserLoginResponseDto;
import org.example.schedulesjpa.userdto.UserResponseDto;
import org.example.schedulesjpa.userdto.UserSignUpResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //회원가입 서비스
    public UserSignUpResponseDto signUp(String username, String password, String email) {

        User user = new User(username, password, email);
        User saveuser = userRepository.save(user);

        return new UserSignUpResponseDto(saveuser.getId(), saveuser.getUsername(), saveuser.getEmail());
    }

    //로그인 서비스
    public UserLoginResponseDto login(String email, String password) {

            User user = userRepository.findByEmailOrElseThrow(email);
//        System.out.println(user.getEmail());
//        System.out.println(email);

        //1. 올바르게 로그인 한 경우
        if(user.getEmail().equals(email) && user.getPassword().equals(password)) {
            return new UserLoginResponseDto(user.getUsername(), user.getEmail());
        }
        else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

    }

    //멤버 전체 조회 기능
    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::toUserDto)
                .toList();
    }

    //멤버 단건 조회 기능
    public UserResponseDto findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Not exist id" + id);
        }
        User findUser = optionalUser.get();

        return new UserResponseDto(findUser.getUsername(), findUser.getEmail());
    }

    //삭제 기능
    public void deleteUser(Long id) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        userRepository.delete(findUser);
    }

}
