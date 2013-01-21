/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uigeeks.biking;

/**
 *
 * @author Martin
 */
public class MapViewCallback {

    public void showEvent(String title, String type, String description, String imageUrl) {
        EventController.getInstance().showEvent(new Event(title, description, imageUrl));
    }
}
