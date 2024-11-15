package org.example.schedulesjpa.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.schedulesjpa.schedulesdto.SchedulesRequestDto;
import org.example.schedulesjpa.schedulesdto.SchedulesResponseDto;
import org.example.schedulesjpa.schedulesdto.SchedulesWithUserNameResponseDto;
import org.example.schedulesjpa.service.SchedulesService;
import org.example.schedulesjpa.userdto.UserLoginResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedulesjpa")
@RequiredArgsConstructor
public class SchedulesController {

    private final SchedulesService schedulesService;

    //전체 조회 하기
    @GetMapping
    public ResponseEntity<List<SchedulesResponseDto>> findAll() {
        List<SchedulesResponseDto> schedulesResponseDtoList = schedulesService.findAll();

        return new ResponseEntity<>(schedulesResponseDtoList, HttpStatus.OK);
    }

    // 단건조회하기
    @GetMapping("/{id}")
    public ResponseEntity<SchedulesWithUserNameResponseDto> findById(@PathVariable Long id) {
        SchedulesWithUserNameResponseDto schedulesWithUserNameResponseDto = schedulesService.findById(id);
        return new ResponseEntity<>(schedulesWithUserNameResponseDto, HttpStatus.OK);
    }

    // 일정 저장
    @PostMapping("/saveschedule")
    public ResponseEntity<SchedulesResponseDto> saveSchedule(@RequestBody SchedulesRequestDto requestDto, HttpServletRequest request) {

        HttpSession httpSession = request.getSession();
        UserLoginResponseDto userLoginResponseDto = (UserLoginResponseDto) httpSession.getAttribute("user");

        SchedulesResponseDto schedulesResponseDto = schedulesService.saveSchedule(
                requestDto.getTitle(),
                requestDto.getContents(),
                userLoginResponseDto.getEmail()
        );
        HttpSession session = request.getSession();
        session.setAttribute("schedules", schedulesResponseDto);
        return new ResponseEntity<>(schedulesResponseDto, HttpStatus.CREATED);
    }

    // 일정 수정
    @PatchMapping("/{id}")
    public ResponseEntity<SchedulesResponseDto> updateSchedule(@PathVariable Long id, @RequestBody SchedulesRequestDto requestDto, HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        UserLoginResponseDto userLoginResponseDto = (UserLoginResponseDto) httpSession.getAttribute("user");
//        System.out.println(userLoginResponseDto.getUsername());

        if(httpSession != null) {
            SchedulesResponseDto schedulesResponseDto = schedulesService.updateSchedule(
                    id,
                    requestDto.getTitle(),
                    requestDto.getContents(),
                    userLoginResponseDto.getEmail()
            );
            return new ResponseEntity<>(schedulesResponseDto, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        schedulesService.deleteSchedule(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
