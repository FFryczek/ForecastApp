package com.ffryczek.Forecast.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ffryczek.Forecast.DTO.DailyDTO;
import com.ffryczek.Forecast.DTO.DailyResponseDTO;
import com.ffryczek.Forecast.DTO.MeteoDTO;
import com.ffryczek.Forecast.DTO.MeteoResponseDTO;
import com.ffryczek.Forecast.DTO.TatryPeaks;

@Service
public class MeteoService {
    private final RestTemplate restTemplate;

    public MeteoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    private List<LocalDate> getTime2date(List<Date> time) {
        List<LocalDate> outputList = new ArrayList<>();
        for (Date timeDate : time) {
            outputList.add(timeDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate());
        }
        return outputList;

    }

    public MeteoResponseDTO getMeteoResponseDTO(String mountainName) {
        List<Float> longitudeAndLatitude = TatryPeaks.peaks.get(mountainName);
        Float latitude = longitudeAndLatitude.get(0);
        Float longitude = longitudeAndLatitude.get(1);
        String url = "https://api.open-meteo.com/v1/forecast?latitude="
                + latitude.toString() + "&longitude=" + longitude.toString()
                + "&daily=temperature_2m_max,temperature_2m_min,rain_sum,showers_sum&current=temperature_2m,rain,showers&timezone=auto&forecast_days=3";

        return this.restTemplate.getForObject(url, MeteoResponseDTO.class);
    }
    
    public MeteoDTO getMeteoDTOFromMeteoResponseDTO(MeteoResponseDTO inputDTO) {
        DailyResponseDTO dailyResponseDTO = inputDTO.getDaily();
        DailyDTO dailyDTO = new DailyDTO(dailyResponseDTO.getTime(),
                dailyResponseDTO.getTemperature_2m_max(),
                dailyResponseDTO.getTemperature_2m_min(),
                dailyResponseDTO.getRain_sum(),
                dailyResponseDTO.getShowers_sum(),
                this.getTime2date(dailyResponseDTO.getTime()));

        return new MeteoDTO(inputDTO.getElevation(), inputDTO.getCurrent(), dailyDTO);
    }
    
    public MeteoDTO getMeteoDTO(String mountainName) {
        return this.getMeteoDTOFromMeteoResponseDTO(this.getMeteoResponseDTO(mountainName));
    }
}
