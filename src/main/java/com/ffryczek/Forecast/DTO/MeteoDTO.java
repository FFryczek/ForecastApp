package com.ffryczek.Forecast.DTO;


import lombok.*;

@Data
@AllArgsConstructor
public class MeteoDTO {

    private double elevation;
    private CurrentResponseDTO current;
    private DailyDTO daily;

    
}
