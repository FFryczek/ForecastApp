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
import com.ffryczek.Forecast.DTO.HourlyDTO;
import com.ffryczek.Forecast.DTO.HourlyResponseDTO;
import com.ffryczek.Forecast.DTO.Hourly;
import com.ffryczek.Forecast.DTO.MeteoDTO;
import com.ffryczek.Forecast.DTO.MeteoResponseDTO;
import com.ffryczek.Forecast.DTO.TatryPeaks;

@Service
public class MeteoService {
    private final RestTemplate restTemplate;

    public MeteoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //PRIVATE METHODS

    private List<LocalDate> getDate2LocalDate(List<Date> date) {
        List<LocalDate> outputList = new ArrayList<>();
        for (Date timeDate : date) {
            outputList.add(timeDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate());
        }
        return outputList;

    }

    private List<String> getHours(List<String> date) {
        List<String> outputList = new ArrayList<>();
        for (String dateString : date) {
            outputList.add(dateString.substring(11, 16));
        }
        return outputList;
    }




    //PUBLIC

    public Double getLongitude(String mountainName) {
        List<Double> longitudeAndLatitude = TatryPeaks.peaks.get(mountainName);
        return longitudeAndLatitude.get(1);
    }
    public Double getLatitude(String mountainName) {
        List<Double> longitudeAndLatitude = TatryPeaks.peaks.get(mountainName);
        return longitudeAndLatitude.get(0);
    }

    public MeteoResponseDTO getMeteoResponseDTO(String mountainName) {
        Double latitude = this.getLatitude(mountainName);
        Double longitude = this.getLongitude(mountainName);
        String url = "https://api.open-meteo.com/v1/forecast?latitude="
                + latitude.toString() + "&longitude=" + longitude.toString()
                + "&daily=temperature_2m_max,temperature_2m_min,rain_sum,showers_sum,snowfall_sum&current=temperature_2m,rain,showers,snowfall,relative_humidity_2m&forecast_days=16";

        return this.restTemplate.getForObject(url, MeteoResponseDTO.class);
    }

    public HourlyResponseDTO getHourlyResponseDTO(String mountainName, String date) {
        Double latitude = this.getLatitude(mountainName);
        Double longitude = this.getLongitude(mountainName);
        String url = "https://api.open-meteo.com/v1/forecast?"
                + "latitude=" + latitude + "&longitude=" + longitude + "&hourly=temperature_2m,relative_humidity_2m,apparent_temperature,rain,showers,snowfall,snow_depth,surface_pressure,cloud_cover,visibility,uv_index&temporal_resolution=hourly_3&"
                + "start_date=" + date + "&end_date=" + date;
        
        return this.restTemplate.getForObject(url, HourlyResponseDTO.class);
    }
    
    public MeteoDTO getMeteoDTOFromMeteoResponseDTO(MeteoResponseDTO inputDTO) {
        DailyResponseDTO dailyResponseDTO = inputDTO.getDaily();
        DailyDTO dailyDTO = new DailyDTO(dailyResponseDTO.getTime(),
                dailyResponseDTO.getTemperature_2m_max(),
                dailyResponseDTO.getTemperature_2m_min(),
                dailyResponseDTO.getRain_sum(),
                dailyResponseDTO.getShowers_sum(),
                dailyResponseDTO.getSnowfall_sum(),
                this.getDate2LocalDate(dailyResponseDTO.getTime()));

        return new MeteoDTO(inputDTO.getElevation(), inputDTO.getCurrent(), dailyDTO);
    }

    public HourlyDTO getHourlyDTOFromHourlyResponseDTO(Hourly inputDTO) {
        return new HourlyDTO(
            inputDTO.getTime().get(0).substring(0,10),
            this.getHours(inputDTO.getTime()),
            inputDTO.getTemperature_2m(),
            inputDTO.getRelative_humidity_2m(),
            inputDTO.getApparent_temperature(),
            inputDTO.getRain(),
            inputDTO.getShowers(),
            inputDTO.getSnowfall(),
            inputDTO.getSnow_depth(),
            inputDTO.getSurface_pressure(),
            inputDTO.getCloud_cover(),
            inputDTO.getVisibility(),
            inputDTO.getUv_index());
    }
    
    public MeteoDTO getMeteoDTO(String mountainName) {
        return this.getMeteoDTOFromMeteoResponseDTO(this.getMeteoResponseDTO(mountainName));
    }

    public HourlyDTO getHourlyDTO(String mountainName, String date) {
        return this.getHourlyDTOFromHourlyResponseDTO(this.getHourlyResponseDTO(mountainName, date).getHourly());
    }
}
