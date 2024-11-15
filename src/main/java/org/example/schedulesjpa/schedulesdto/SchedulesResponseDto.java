package org.example.schedulesjpa.schedulesdto;


import lombok.Getter;
import org.example.schedulesjpa.entity.Schedules;

import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
public class SchedulesResponseDto {

    private final Long id;
    private final String title;
    private final String contents;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;

    public SchedulesResponseDto(Long id, String title, String contents, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }

    public static SchedulesResponseDto toDto(Schedules schedules) {
        return new SchedulesResponseDto(schedules.getId(), schedules.getTitle(), schedules.getContents(), schedules.getCreateAt(), schedules.getModifiedAt());
    }
}
