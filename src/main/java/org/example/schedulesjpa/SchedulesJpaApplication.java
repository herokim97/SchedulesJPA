package org.example.schedulesjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SchedulesJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchedulesJpaApplication.class, args);
    }

}
