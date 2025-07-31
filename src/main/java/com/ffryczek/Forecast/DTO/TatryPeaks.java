package com.ffryczek.Forecast.DTO;
import lombok.experimental.UtilityClass;
import java.util.*;

@UtilityClass
public class TatryPeaks {
    public final Map<String, List<Float>> peaks = createPeaks();

    private Map<String, List<Float>> createPeaks() {
        Map<String, List<Float>> map = new HashMap<>();
        map.put("Kozi Wierch", Arrays.asList(49.21833f, 20.02861f));        
        map.put("Szpiglasowy Wierch", Arrays.asList(49.19752f, 20.04030f)); 
        map.put("Rysy", Arrays.asList(49.17934f, 20.08847f));                
        map.put("Kościelec", Arrays.asList(49.22518f, 20.01458f));        
        map.put("Świnica", Arrays.asList(49.21944f, 20.00917f));
        return Collections.unmodifiableMap(map);
    }
}
