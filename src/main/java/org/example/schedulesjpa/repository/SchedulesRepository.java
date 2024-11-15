package org.example.schedulesjpa.repository;

import org.example.schedulesjpa.entity.Schedules;
import org.example.schedulesjpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface SchedulesRepository extends JpaRepository<Schedules, Long> {
    default Schedules findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "id not exist = " + id));

    }
}
