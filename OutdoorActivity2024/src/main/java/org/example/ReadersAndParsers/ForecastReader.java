package org.example.ReadersAndParsers;

import org.example.Entities.Astro;
import org.example.Entities.Forecast;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ForecastReader implements Reader<List<List<Forecast>>> {

    private static final AstroReader astroReader = new AstroReader();
    private static final LocationReader locationReader = new LocationReader();

    @Override
    public List<List<Forecast>> read(String responseBody) {
        JSONObject jsonResponse = new JSONObject(responseBody);
        JSONArray forecastDayArray = jsonResponse.getJSONObject("forecast").getJSONArray("forecastday");
        List<List<Forecast>> days = new ArrayList<>();
        for (int i = 0; i < forecastDayArray.length(); i++) {
            JSONArray hourArray = forecastDayArray.getJSONObject(i).getJSONArray("hour");
            Astro astro = astroReader.read(responseBody);
            String tz_id = locationReader.read(responseBody);
            List<Forecast> hours = new ArrayList<>();
            for (int j = 0; j < hourArray.length(); j++) {
                JSONObject hour = hourArray.getJSONObject(j);
                LocalDateTime localDateTime = LocalDateTimeParser.parseDateTime(hour.getString("time"));
                LocalDateTime sofiaDateTime = locationReader.convertToTimeZone(localDateTime, tz_id);
                double wind_kph = hour.getDouble("gust_kph");
                int chanceOfRain = hour.getInt("chance_of_rain");
                double temperature = hour.getDouble("temp_c");
                Forecast forecast = new Forecast(sofiaDateTime, wind_kph, chanceOfRain, temperature, astro);
                hours.add(forecast);
            }
            days.add(hours);
        }

        return days;
    }
}
