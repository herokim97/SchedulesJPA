package org.example.schedulesjpa.service;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.schedulesjpa.entity.Schedules;
import org.example.schedulesjpa.entity.User;
import org.example.schedulesjpa.repository.SchedulesRepository;
import org.example.schedulesjpa.repository.UserRepository;
import org.example.schedulesjpa.schedulesdto.SchedulesResponseDto;
import org.example.schedulesjpa.schedulesdto.SchedulesWithUserNameResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchedulesService {
    private final SchedulesRepository schedulesRepository;
    private final UserRepository userRepository;

    //스케줄 저장
    public SchedulesResponseDto saveSchedule(String title, String contents, String email) {

        User user = userRepository.findByEmailOrElseThrow(email);

        Schedules schedules = new Schedules(title, contents, user);

        Schedules saveSchedules = schedulesRepository.save(schedules);

        return new SchedulesResponseDto(saveSchedules.getId(), saveSchedules.getTitle(), saveSchedules.getContents());

    }

    //스케줄 전체 조회
    public List<SchedulesResponseDto> findAll() {
        return schedulesRepository.findAll()
                .stream()
                .map(SchedulesResponseDto::toDto)
                .toList();
    }

    //스케줄 단건 조회
    public SchedulesWithUserNameResponseDto findById(Long id) {
        Schedules findSchedules = schedulesRepository.findByIdOrElseThrow(id);
        User writer = findSchedules.getUser();

        return new SchedulesWithUserNameResponseDto(findSchedules.getId(),findSchedules.getTitle(), findSchedules.getContents(), writer.getUsername());
    }

    //스케줄 수정
    public SchedulesResponseDto updateSchedule(Long id, String title, String contents, String email) {
        Schedules findSchedules = schedulesRepository.findByIdOrElseThrow(id);
        findSchedules.setUser(userRepository.findByEmailOrElseThrow(email));

        findSchedules.setTitle(title);
        findSchedules.setContents(contents);

        // 변경된 객체를 저장
        Schedules updateSchedules = schedulesRepository.save(findSchedules);

        return new SchedulesResponseDto(updateSchedules.getId(), updateSchedules.getTitle(), updateSchedules.getContents());


    }

    //스케줄 삭제
    public void deleteSchedule(Long id) {
        Schedules findSchedules = schedulesRepository.findByIdOrElseThrow(id);

        schedulesRepository.delete(findSchedules);
    }


}
