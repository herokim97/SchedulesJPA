package org.example.schedulesjpa.schedulesdto;


import lombok.Getter;
import org.example.schedulesjpa.entity.Schedules;

@Getter
public class SchedulesResponseDto {

    private final Long id;
    private final String title;
    private final String contents;

    public SchedulesResponseDto(Long id, String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
    }

    public static SchedulesResponseDto toDto(Schedules schedules) {
        return new SchedulesResponseDto(schedules.getId(), schedules.getTitle(), schedules.getContents());
    }
}
