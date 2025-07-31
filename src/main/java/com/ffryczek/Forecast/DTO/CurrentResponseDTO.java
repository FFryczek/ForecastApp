package com.ffryczek.Forecast.DTO;

import lombok.*;

@Data
@NoArgsConstructor
public class CurrentResponseDTO {
    private String temperature_2m;
    private double rain;
    private double showers;
}
