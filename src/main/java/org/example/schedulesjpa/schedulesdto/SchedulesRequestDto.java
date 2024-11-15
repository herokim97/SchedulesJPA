package org.example.schedulesjpa.schedulesdto;

import lombok.Getter;

@Getter
public class SchedulesRequestDto {


    private final String title;
    private final String contents;

    public SchedulesRequestDto( String title, String contents) {

        this.title = title;
        this.contents = contents;
    }


}
