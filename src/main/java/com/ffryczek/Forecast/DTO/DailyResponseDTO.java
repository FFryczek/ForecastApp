package com.ffryczek.Forecast.DTO;

import java.util.Date;
import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
public class DailyResponseDTO {
    private List<Date> time;
    private List<Double> temperature_2m_max;
    private List<Double> temperature_2m_min;
    private List<Double> rain_sum;
    private List<Double> showers_sum;
    private List<Double> snowfall_sum;
}
