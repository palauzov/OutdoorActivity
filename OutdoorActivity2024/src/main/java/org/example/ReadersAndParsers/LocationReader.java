package org.example.ReadersAndParsers;

import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class LocationReader implements Reader<String>{
    @Override
    public String read(String responseBody) {
        JSONObject jsonObject = new JSONObject(responseBody);
        return jsonObject.getJSONObject("location").getString("tz_id");
    }

    public LocalDateTime convertToTimeZone(LocalDateTime time, String tz_id){
        ZoneId currentZoneId = ZoneId.of(tz_id);
        ZoneId sofiaZoneId = ZoneId.of("Europe/Sofia");
        ZonedDateTime currentZonedDateTime = time.atZone(currentZoneId);
        ZonedDateTime sofiaZonedDateTime = currentZonedDateTime.withZoneSameInstant(sofiaZoneId);
        return sofiaZonedDateTime.toLocalDateTime();
    }
}
