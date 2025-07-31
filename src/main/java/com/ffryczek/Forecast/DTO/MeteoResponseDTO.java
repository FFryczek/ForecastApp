package com.ffryczek.Forecast.DTO;
import lombok.*;

@Data
@NoArgsConstructor
public class MeteoResponseDTO {

    private double elevation;
    private CurrentResponseDTO current;
    private DailyResponseDTO daily;


}
