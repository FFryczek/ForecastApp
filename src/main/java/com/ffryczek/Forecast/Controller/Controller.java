package com.ffryczek.Forecast.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ffryczek.Forecast.DTO.MeteoDTO;
import com.ffryczek.Forecast.Service.MeteoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/meteo")
public class Controller {
    private final MeteoService meteoService;

    public Controller(MeteoService meteoService) {
        this.meteoService = meteoService;
    }

    @GetMapping("/{mountainName}")
    public MeteoDTO getMeteoDTO(@PathVariable String mountainName) {
       return this.meteoService.getMeteoDTO(mountainName);
    }

}
