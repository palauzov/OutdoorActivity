package org.example.ReadersAndParsers;

import org.example.Entities.Astro;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class AstroReader implements Reader{
    private static LocationReader locationReader = new LocationReader();
    @Override
    public Astro read(String jsonBody){
        JSONObject jsonResponse = new JSONObject(jsonBody);
        JSONArray forecastDayArray = jsonResponse.getJSONObject("forecast").getJSONArray("forecastday");
        JSONObject astroObject = forecastDayArray.getJSONObject(0).getJSONObject("astro");

        String tz_id = locationReader.read(jsonBody);
        LocalTime sunset = LocalDateTimeParser.parseTime(astroObject.getString("sunset"));
        LocalTime sunrise = LocalDateTimeParser.parseTime(astroObject.getString("sunrise"));

        ZoneId currentZoneId = ZoneId.of(tz_id);
        ZoneId sofiaZoneId = ZoneId.of("Europe/Sofia");

        ZonedDateTime currentZonedDateTimeSunset = sunset.atDate(java.time.LocalDate.now()).atZone(currentZoneId);
        ZonedDateTime sofiaZonedDateTimeSunset = currentZonedDateTimeSunset.withZoneSameInstant(sofiaZoneId);
        LocalTime sofiaTimeSunset = sofiaZonedDateTimeSunset.toLocalTime();

        ZonedDateTime currentZonedDateTimeSunrise = sunrise.atDate(java.time.LocalDate.now()).atZone(currentZoneId);
        ZonedDateTime sofiaZonedDateTimeSunrise = currentZonedDateTimeSunrise.withZoneSameInstant(sofiaZoneId);
        LocalTime sofiaTimeSunrise = sofiaZonedDateTimeSunrise.toLocalTime();


        return new Astro(sofiaTimeSunrise, sofiaTimeSunset);
    }



}
