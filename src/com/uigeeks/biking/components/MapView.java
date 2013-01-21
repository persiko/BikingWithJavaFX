package com.uigeeks.biking.components;

import com.uigeeks.biking.Config;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;

/**
 *
 * @author Martin
 */
public class MapView extends Pane {

    WebView webView = null;

    public MapView() {
        webView = new WebView();
        getChildren().add(webView);
    }
}
