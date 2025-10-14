package com.um.prog2.demoobserver.display;

import com.um.prog2.demoobserver.model.WeatherData;
import com.um.prog2.demoobserver.observer.Observer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HumidityDisplay implements Observer<WeatherData>{

    private float currentHumidity;


    public HumidityDisplay(){
        this.currentHumidity = 0.0f;
        log.info("Display de humedad creado");
    }

    @Override
    public void update(WeatherData weatherData) {
        if (weatherData != null){
            this.currentHumidity = weatherData.getHumidity();
            display();
        }else{
            log.warn("Datos meteorologicos nulos");
        }
    }

    private void display(){
        log.info("Display de Humedad {} %", String.format("%.1f", this.currentHumidity));
    }

    public float getCurrentHumidity(){
        return this.currentHumidity;
    }
}
