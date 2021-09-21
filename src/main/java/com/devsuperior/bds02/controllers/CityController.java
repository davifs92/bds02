package com.devsuperior.bds02.controllers;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cities")
public class CityController {


    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<List<CityDTO>> findAll(){

        List<CityDTO> list = cityService.findAll();

        return ResponseEntity.ok().body(list);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CityDTO> delete(@PathVariable Long id){
        if(cityService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
            cityService.deleteById(id);
            return ResponseEntity.noContent().build();
    }
}
