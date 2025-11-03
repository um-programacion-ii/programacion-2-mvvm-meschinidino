package com.um.prog2.demoobserver.subject.implementation;

import com.um.prog2.demoobserver.model.WeatherData;
import com.um.prog2.demoobserver.observer.Observer;
import com.um.prog2.demoobserver.subject.Subject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;


@Slf4j

public class WeatherStation implements Subject<WeatherData> {

    private final List<Observer<WeatherData>> observers = new ArrayList<>();

    @Getter
    private WeatherData currentWeatherData;

    @Override
    public void registerObserver(Observer<WeatherData> observer) {
        observers.add(observer);
        log.info("Nuevo observador registrado. Total de observadores: {}", observers.size());
    }

    @Override
    public void removeObserver(Observer<WeatherData> observer) {
        observers.remove(observer);
        log.info("Observador removido. Total de observadores: {}", observers.size());
    }

    @Override
    public void notifyObservers() {
        log.info("Notificando a {} observadores sobre cambios climaticos", observers.size());
        for (var observer : observers) {
            observer.update(currentWeatherData);
        }
    }

    @Override
    public List<Observer<WeatherData>> getObservers() {
        return new ArrayList<>(observers);
    }

    public void setMeasurements(float temperature, float humidity, float pressure){
        log.info("Actualizando mediciones - Temp: {} °C, Humedad {} %, Presion {} hPa", temperature, humidity, pressure);
        this.currentWeatherData = new WeatherData(temperature, humidity, pressure);
        measurementsChanged();
    }

    public void setMeasurements(float temperature, float humidity){
        log.info("Actualizando mediciones - Temp: {} °C, Humedad {} %", temperature, humidity);
        this.currentWeatherData = new WeatherData(temperature, humidity);
        measurementsChanged();
    }

    private void measurementsChanged(){
        log.info("Notificando a obsjervadores sobre mediciones actualizadas");
        notifyObservers();
    }

}
