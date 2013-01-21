package com.uigeeks.biking;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DisplacementMap;
import javafx.scene.effect.FloatMap;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class CarbonPaper extends Application {

	public static void main(String[] args) {
		Application.launch(CarbonPaper.class);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		WritableImage writableImage = new WritableImage(512, 376);
		PixelWriter pixelWriter = writableImage.getPixelWriter();

		int xCenter = 256;
		int yCenter = 188;

		// ImageView imageView = new
		// ImageView(getClass().getResource("map.png").toString());
		WebView view = new WebView();
		view.setMaxSize(512, 376);
		final WebEngine engine = view.getEngine();

		// engine.setOnAlert(new EventHandler<WebEvent<String>>() {
		//
		// @Override
		// public void handle(WebEvent<String> event) {
		// pane.getChildren().add(new ImageView(event.getData()));
		//
		// }
		// });

		engine.setJavaScriptEnabled(true);
		engine.load(getClass().getResource("map.html").toString());

		DisplacementMap dMap = new DisplacementMap();
		FloatMap floatMap = new FloatMap(512, 376);
		dMap.setMapData(floatMap);
		view.setEffect(dMap);

		float span = 20;

		for (int x = 0; x < writableImage.getWidth(); x++) {

			for (int y = 0; y < writableImage.getHeight(); y++) {

				// Calculate distance from center
				float xDistance = x - xCenter;
				float yDistance = y - yCenter;
				double distance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);

				if (distance > 100 && distance < 100 + span) {

					// between 0 and 1
					distance = (distance - 100) / span;

					double colorAdjustement = Math.sin(distance * Math.PI / 2);
					int r = 200;

					pixelWriter.setColor(x, y, Color.rgb((int) (r * colorAdjustement), (int) (r * colorAdjustement), (int) (r * colorAdjustement),  1-colorAdjustement));

					float d1 = (float) Math.sin(distance * Math.PI);
					float d2 = (float) Math.sin(distance * Math.PI);

					if (xDistance < 0) {
						d1 *= -1;
					}

					if (yDistance < 0) {
						d2 *= -1;
					}

					floatMap.setSamples(x, y, d1 / (20f + (float) (25 * (1-distance))), d2 / (20f + (float) (25 * (1-distance))));
				} else if (distance <= 100) {

					pixelWriter.setColor(x, y, Color.rgb(130, 130, 130, 0.5f));
					float d1 = (float) Math.sin(100 * Math.PI);
					float d2 = (float) Math.sin(100 * Math.PI);
					if (xDistance < 0) {
						d1 *= -1;
					}

					if (yDistance < 0) {
						d2 *= -1;
					}
					floatMap.setSamples(x, y, d1 / 20f, d2 / 20f);

				} else {

					floatMap.setSample(x, y, 0, 0);
					// pixelWriter.setColor(x, y, Color.rgb(0,0,0,0.0));

				}

			}

		}

		Pane pane = new Pane();

		ImageView image = new ImageView(writableImage);
		image.setBlendMode(BlendMode.SRC_ATOP);

		pane.getChildren().add(view);
		pane.getChildren().add(image);

		pane.getChildren().add(new Circle(xCenter, yCenter, 100, Color.rgb(200, 200, 200, 0.4f)));
		ImageView phone = new ImageView(getClass().getResource("phone.png").toString());
		phone.setLayoutX(xCenter - 45);
		phone.setLayoutY(yCenter - 45);
		pane.getChildren().add(phone);

		Scene scene = new Scene(pane, 512, 376);
		primaryStage.setScene(scene);
		primaryStage.show();

		// HBox box = new HBox();
		// box.setStyle("-fx-background-color: green;");
		// ImageView image = new ImageView(writableImage);
		// box.getChildren().add(image);
		// box.getChildren().add(imageView);
		//
		// Scene scene = new Scene(box);
		// primaryStage.setScene(scene);
		// primaryStage.show();
	}
}
