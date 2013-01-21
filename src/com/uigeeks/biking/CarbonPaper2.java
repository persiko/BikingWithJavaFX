package com.uigeeks.biking;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class CarbonPaper2 extends Application {

	public static void main(String[] args) {
		Application.launch(CarbonPaper2.class);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		InnerShadow innerShadow = new InnerShadow();
		innerShadow.setOffsetX(14);
		innerShadow.setOffsetY(14);
		innerShadow.setColor(Color.web("0x3b596d"));

		Rectangle rectangle = new Rectangle(200, 200);
		rectangle.setLayoutX(100);
		rectangle.setLayoutY(100);
		rectangle.setStyle("-fx-fill: #99999977");
		rectangle.setEffect(innerShadow);

		Pane pane = new Pane();
		pane.getChildren().add(new ImageView(getClass().getResource("map.png").toString()));
		pane.getChildren().add(rectangle);

		Scene scene = new Scene(pane, 800, 600);

		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
