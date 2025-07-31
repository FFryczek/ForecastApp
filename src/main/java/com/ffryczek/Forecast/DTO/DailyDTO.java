package com.ffryczek.Forecast.DTO;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class DailyDTO {

    private List<Date> time;
    private List<Double> temperature_2m_max;
    private List<Double> temperature_2m_min;
    private List<Double> rain_sum;
    private List<Double> showers_sum;
    private List<Double> snowfall_sum;
    private List<LocalDate> date;

}
