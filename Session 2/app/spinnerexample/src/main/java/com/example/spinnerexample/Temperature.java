package com.example.spinnerexample;

public class Temperature {
    private float temperature;

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float convertFahrenheitToCelsius() {
        return ((temperature - 32) * 5 / 9);
    }

    public float convertCelsiusToFahrenheit() {
        return ((temperature * 9) / 5) + 32;
    }
}
