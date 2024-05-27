package org.example.Entities;
import java.time.LocalDateTime;

public class Forecast {

    public Forecast(LocalDateTime dateTime, double wind_kph, int chanceOfRain, double temperature, Astro astro){
          this.dateTime = dateTime;
          this.wind_kph = wind_kph;
          this.chanceOfRain = chanceOfRain;
          this.temperature = temperature;
          this.astro = astro;
    }
    private LocalDateTime dateTime;

    private double wind_kph;

    private int chanceOfRain;

    private double temperature;

    private Astro astro;




    public Astro getAstro() {
        return astro;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getWind_kph() {
        return wind_kph;
    }

    public int getChanceOfRain() {
        return chanceOfRain;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setAstro(Astro astro) {
        this.astro = astro;
    }

    public void setChanceOfRain(int chanceOfRain) {
        this.chanceOfRain = chanceOfRain;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setWind_kph(double wind_kph) {
        this.wind_kph = wind_kph;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "dateTime=" + dateTime +
                ", wind_kph=" + wind_kph +
                ", chanceOfRain=" + chanceOfRain +
                ", temperature=" + temperature +
                ", sunrise=" + astro.getSunrise() +
                ", sunset=" + astro.getSunset() +
                '}';
    }
}
