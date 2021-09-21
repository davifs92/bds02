package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repository.EventRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRespository eventRespository;

    @Autowired
    private CityService cityService;

    public List<EventDTO> findAll(){
        List<Event> list = eventRespository.findAll(Sort.by(""));
        return list.stream().map(x -> new EventDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public EventDTO update(Long id, EventDTO dto) {
       Event entityDb = eventRespository.getOne(id);


        AtomicReference<City> city  = new AtomicReference<>(new City());
        cityService.findById(dto.getCityId()).ifPresent(cityValue -> city.set(cityValue));

        entityDb.setName(dto.getName());
        entityDb.setDate(dto.getDate());
        entityDb.setUrl(dto.getUrl());
        entityDb.setCity(city.get());

        entityDb = eventRespository.save(entityDb);
        return new EventDTO(entityDb);
    }
}
