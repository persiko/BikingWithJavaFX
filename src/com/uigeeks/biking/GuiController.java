/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uigeeks.biking;

import com.uigeeks.biking.data.Weather;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.web.WebView;

/**
 *
 * @author Martin
 */
public class GuiController implements Initializable {

    @FXML
    private Label weatherLabel;
    @FXML
    private ToolBar mapToolbar;
    @FXML
    private Region mapToolbarSpring;
    @FXML
    private WebView mapView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initMap();
        initWeather();

        // Grow center spacer in map toolbar
        HBox.setHgrow(mapToolbarSpring, Priority.ALWAYS);
    }

    private void initWeather() {
        Weather weather = Weather.getInstance();
        weatherLabel.setText(weather.getTemperature());
        weatherLabel.setGraphic(new ImageView(weather.getWeatherIcon()));
        HBox.setMargin(weatherLabel, new Insets(0, 0, 0, 5));
    }

    private void initMap() {
        mapView.getEngine().load(Config.mapUrl);
    }
}
