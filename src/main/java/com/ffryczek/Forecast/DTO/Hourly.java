package com.ffryczek.Forecast.DTO;

import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
public class Hourly {
    private List<String> time;
    private List<Double> temperature_2m;
    private List<Integer> relative_humidity_2m;
    private List<Double> apparent_temperature;
    private List<Double> rain;
    private List<Double> showers;
    private List<Double> snowfall;
    private List<Double> snow_depth;
    private List<Double> surface_pressure;
    private List<Integer> cloud_cover;
    private List<Double> visibility;
    private List<Double> uv_index;
}
