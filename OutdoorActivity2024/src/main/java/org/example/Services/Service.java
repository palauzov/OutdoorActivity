package org.example.Services;

import org.example.Entities.Forecast;
import org.example.Entities.ForecastCriteria;

import java.util.ArrayList;
import java.util.List;

public class Service {

    public List<Forecast> removeNightHours(List<List<Forecast>> days){
        List<Forecast> filtered = new ArrayList<>();
        for (int i = 0; i < days.size(); i++) {
            for (int j = 0; j < days.get(i).size(); j++) {
                Forecast current = days.get(i).get(j);
                if (current.getDateTime().getHour() > current.getAstro().getSunrise().getHour() &&
                current.getDateTime().getHour() < current.getAstro().getSunset().getHour()){
                    filtered.add(current);
                }
            }
        }
        return filtered;
    }

    public List<Forecast> bestHoursForPlayFilter(List<Forecast> hoursWithSun, ForecastCriteria forecastCriteria){

        List<Forecast> filtered = new ArrayList<>();

        for (int i = 0; i < hoursWithSun.size(); i++) {
            if (hoursWithSun.get(i).getWind_kph() < forecastCriteria.getWind_kph() &&
                    hoursWithSun.get(i).getChanceOfRain() <= forecastCriteria.getChanceOfRain() &&
                    (hoursWithSun.get(i).getTemperature() > forecastCriteria.getMinTemp() &&
                    hoursWithSun.get(i).getTemperature() < forecastCriteria.getMaxTemp())){

               filtered.add(hoursWithSun.get(i));
            }
        }

        return filtered;
    }

    public String printBestHours(List<Forecast> bestHoursForPlay, ForecastCriteria forecastCriteria){
        StringBuilder sb = new StringBuilder();
        sb.append("Hours for sport " + forecastCriteria.getSportsName() + " are:\n");
        for (int i = 0; i < bestHoursForPlay.size() - 1; i++) {
            if(bestHoursForPlay.get(i).getDateTime().toLocalDate().isEqual
                    (bestHoursForPlay.get(i + 1).getDateTime().toLocalDate())){
                int isEqual = bestHoursForPlay.get(i).getDateTime().toLocalTime().compareTo
                        (bestHoursForPlay.get(i + 1).getDateTime().toLocalTime().minusHours(1));
                if (isEqual == 0){
                   sb.append(bestHoursForPlay.get(i).getDateTime() + " - " + bestHoursForPlay.get(i + 1).getDateTime().toLocalTime().plusHours(1) + "\n");
                }
            }
        }
        return sb.toString();

    }
}
