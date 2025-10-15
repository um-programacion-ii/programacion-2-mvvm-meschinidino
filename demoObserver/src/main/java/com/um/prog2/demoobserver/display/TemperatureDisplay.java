package com.um.prog2.demoobserver.display;

import com.um.prog2.demoobserver.model.WeatherData;
import com.um.prog2.demoobserver.observer.Observer;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class TemperatureDisplay implements Observer<WeatherData> {

    private float currentTemperature;

    public TemperatureDisplay(){
        log.info("Iniciando Display de temperatura");
        this.currentTemperature = 0.0f;
    }

    @Override
    public void update(WeatherData weatherData) {
        if (weatherData != null){
            this.currentTemperature = weatherData.getTemperature();
            display();
        }else{
            log.warn("Datos meteorologicos nulos");

        }
    }
    private void display(){
        log.info("Display de temperatura {} °C", String.format("%.1f", this.currentTemperature));
    }

    public float getCurrentTemperature(){
        return this.currentTemperature;
    }
}
