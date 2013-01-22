/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uigeeks.biking;

import javafx.scene.web.WebView;
import org.w3c.dom.Document;
import org.w3c.dom.html.HTMLImageElement;


/**
 *
 * @author Martin
 */
public class EventController {

    private static EventController instance;
    private WebView eventView;

    private EventController() {
    }

    public static EventController getInstance() {
        if (instance == null) {
            instance = new EventController();
        }
        return instance;
    }

    public void init(WebView eventView) {
        this.eventView = eventView;
        this.eventView.getEngine().load(getClass().getResource("event.html").toExternalForm());
    }

    public void showEvent(Event event) {
        Document document = this.eventView.getEngine().getDocument();
        document.getElementById("title").setTextContent(event.getTitle());
        document.getElementById("description").setTextContent(event.getDescription());

        HTMLImageElement image = (HTMLImageElement) document.getElementById("photo");

        if (event.hasImage()) {
            image.setClassName("");
            image.setSrc(event.getImageUrl());
        } else {
            image.setClassName("hidden");
        }

    }
}
