package org.example.schedulesjpa.schedulesdto;

import lombok.Getter;
import org.example.schedulesjpa.entity.Schedules;

@Getter
public class SchedulesWithUserNameResponseDto {

    private final Long id;
    private final String title;
    private final String contents;
    private final String username;

    public SchedulesWithUserNameResponseDto(Long id, String title, String contents, String username) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.username = username;
    }
}


