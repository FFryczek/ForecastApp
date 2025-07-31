package com.ffryczek.Forecast.DTO;

import lombok.*;

@Data
@NoArgsConstructor
public class CurrentResponseDTO {
    private String temperature_2m;
    private double rain;
    private double showers;
    private double snowfall;
    private int relative_humidity_2m;
}
