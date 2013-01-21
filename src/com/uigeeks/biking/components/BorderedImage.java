package com.uigeeks.biking.components;

import javafx.scene.control.Control;
import javafx.scene.image.ImageView;

public class BorderedImage extends Control{

	private ImageView imageView;
	
	public BorderedImage(ImageView image ) {
		getChildren().add(image);
		imageView = image;
		setId("photo-view");
	}
	
	
}
