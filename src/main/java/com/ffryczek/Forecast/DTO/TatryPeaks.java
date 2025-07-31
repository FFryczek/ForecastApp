package com.ffryczek.Forecast.DTO;
import lombok.experimental.UtilityClass;
import java.util.*;

@UtilityClass
public class TatryPeaks {
    public final Map<String, List<Double>> peaks = createPeaks();

    private Map<String, List<Double>> createPeaks() {
        Map<String, List<Double>> map = new HashMap<>();
        map.put("Kozi Wierch", Arrays.asList(49.203009811, 20.021160965));
        map.put("Szpiglasowy Wierch", Arrays.asList(49.209925445, 20.045520837));
        map.put("Rysy", Arrays.asList(49.179412723, 20.088557565));
        map.put("Kościelec", Arrays.asList(49.229181010, 20.005678293));
        map.put("Świnica", Arrays.asList(49.223610898, 20.009386710));
        map.put("Kasprowy Wierch", Arrays.asList(49.231668703, 19.980281783));
        map.put("Ciemniak", Arrays.asList(49.258563119, 19.892907253));
        map.put("Zawrat", Arrays.asList(49.204877791, 20.005955778));
        map.put("Małołączniak", Arrays.asList(49.215098789, 19.948407103));
        map.put("Wołosań", Arrays.asList(49.241142410, 20.121645595));
        
        
        
        


        return Collections.unmodifiableMap(map);
    }
}
