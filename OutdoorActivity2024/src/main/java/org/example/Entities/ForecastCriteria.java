package org.example.Entities;

import lombok.Getter;


public class ForecastCriteria {
    String sportsName;
    int minTemp;
    int maxTemp;
    int wind_kph;
    int chanceOfRain;

    public ForecastCriteria(){

    }

    public void setWind_kph(int wind_kph) {
        this.wind_kph = wind_kph;
    }

    public void setChanceOfRain(int chanceOfRain) {
        this.chanceOfRain = chanceOfRain;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }

    public void setSportsName(String sportsName) {
        this.sportsName = sportsName;
    }

    public int getChanceOfRain() {
        return chanceOfRain;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public int getWind_kph() {
        return wind_kph;
    }

    public String getSportsName() {
        return sportsName;
    }
}
