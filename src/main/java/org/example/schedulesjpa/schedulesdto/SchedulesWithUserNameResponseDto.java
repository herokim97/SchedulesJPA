package org.example.schedulesjpa.schedulesdto;

import lombok.Getter;
import org.example.schedulesjpa.entity.Schedules;

import java.time.LocalDateTime;

@Getter
public class SchedulesWithUserNameResponseDto {

    private final Long id;
    private final String title;
    private final String contents;
    private final String username;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;

    public SchedulesWithUserNameResponseDto(Long id, String title, String contents, String username, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.username = username;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }
}


