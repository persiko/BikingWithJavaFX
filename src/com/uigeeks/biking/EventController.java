/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uigeeks.biking;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 *
 * @author Martin
 */
public class EventController {

    private static EventController instance;
    private Label titleLabel;
    private ImageView imageView;
    private Label descriptionLabel;

    private EventController() {
    }

    public static EventController getInstance() {
        if (instance == null) {
            instance = new EventController();
        }
        return instance;
    }

    public void init(Label titleLabel, ImageView imageView, Label descriptionLabel) {
        this.titleLabel = titleLabel;
        this.imageView = imageView;
        this.descriptionLabel = descriptionLabel;
    }

    public void showEvent(Event event) {
        // Title
        titleLabel.setText(event.getTitle());

        // Photo
        final String imageUrl = event.getImageUrl();
        if (imageUrl == null || imageUrl.length() == 0) {
            imageView.setScaleY(0);
        } else {
            imageView.setScaleY(1);
            imageView.setVisible(true);
            imageView.setImage(new Image(imageUrl));
        }

        // Description
        descriptionLabel.setText(event.getDescription());
    }
}
