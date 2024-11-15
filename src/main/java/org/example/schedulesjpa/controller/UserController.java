package org.example.schedulesjpa.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.example.schedulesjpa.schedulesdto.SchedulesResponseDto;
import org.example.schedulesjpa.service.UserService;
import org.example.schedulesjpa.userdto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //회원가입하기
    @PostMapping("/signup")
    public ResponseEntity<UserSignUpResponseDto> signUp(@RequestBody UserSignUpRequestDto requestDto){

        UserSignUpResponseDto userSignUpResponseDto = userService.signUp(
                requestDto.getUsername(),
                requestDto.getPassword(),
                requestDto.getEmail()
        );

        return new ResponseEntity<>(userSignUpResponseDto, HttpStatus.CREATED);
    }

    //로그인 기능
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto requestDto, HttpServletRequest request){

        try {
            UserLoginResponseDto userLoginResponseDto = userService.login(
                    requestDto.getEmail(),
                    requestDto.getPassword()
            );

            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("user", userLoginResponseDto);

            return new ResponseEntity<>(userLoginResponseDto, HttpStatus.OK);
        }

        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    //user 조회하기
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto>findById(@PathVariable Long id) {
        UserResponseDto userResponseDto = userService.findById(id);
        return new ResponseEntity<>(userResponseDto,HttpStatus.OK);
    }

    //user 전체 조회
    @GetMapping
    public ResponseEntity<List<UserResponseDto>>findAll() {
        List<UserResponseDto> userResponseDtoList = userService.findAll();

        return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
    }

    //user 삭제하기
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }






}
