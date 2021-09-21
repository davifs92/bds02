package com.devsuperior.bds02.repository;

import com.devsuperior.bds02.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRespository extends JpaRepository<Event, Long> {
}
