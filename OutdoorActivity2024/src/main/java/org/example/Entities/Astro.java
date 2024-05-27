package org.example.Entities;


import java.time.LocalTime;

public class Astro {

    private LocalTime sunset;
    private LocalTime sunrise;

    public Astro(LocalTime sunrise, LocalTime sunset){
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public LocalTime getSunset() {
        return sunset;
    }

    public LocalTime getSunrise() {
        return sunrise;
    }

    public void setSunrise(LocalTime sunrise) {
        this.sunrise = sunrise;
    }

    public void setSunset(LocalTime sunset) {
        this.sunset = sunset;
    }
}
