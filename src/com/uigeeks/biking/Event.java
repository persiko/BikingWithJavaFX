/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uigeeks.biking;

/**
 *
 * @author Martin
 */
public class Event {

    private String title;
    private String description;
    private String imageUrl;

    public Event() {
    }

    public Event(String title, String description, String imageUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @param imageUrl the imageUrl to set
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    boolean hasImage() {
        final String url = this.getImageUrl();
        return url != null && url.length() > 0;
    }
}
