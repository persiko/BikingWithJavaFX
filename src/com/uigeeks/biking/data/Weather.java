package com.uigeeks.biking.data;

import javafx.scene.image.Image;

/**
 * Class responsible for fetching and formatting weather information.
 *
 * @author Martin
 */
public class Weather {

    private static Weather weather;

    public static Weather getInstance() {
        if (weather == null) {
            weather = new Weather();
        }

        return weather;
    }

    /**
     *
     * @return The icon to represent the current weather.
     */
    public Image getWeatherIcon() {
        return new Image(getClass().getResourceAsStream("weather_rain.png"));
    }

    /**
     *
     * @return The current temperature as a nicely formatted string.
     */
    public String getTemperature() {
        return "8°";
    }
}
