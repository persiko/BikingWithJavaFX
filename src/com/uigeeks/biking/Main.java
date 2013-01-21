package com.uigeeks.biking;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;

import com.sun.webpane.webkit.JSObject;
import com.uigeeks.biking.components.BorderedImage;

public class Main extends Application {

	private WebView view;

	private final List<TranslateTransition> transitions = new ArrayList<>();

	private ParallelTransition parallelTransition = new ParallelTransition();

	private final StackPane contentPane = new StackPane();
	private final Pane infoPane = new Pane();

	private final String[] menu = new String[3];

	public static void main(String[] args) {
		Application.launch(Main.class);
	}

	@Override
	public void start(Stage stage) throws Exception {

		menu[0] = getClass().getResource("comments.png").toString();
		menu[1] = getClass().getResource("comments.png").toString();
		menu[2] = getClass().getResource("comments.png").toString();

		view = new WebView();
		final WebEngine engine = view.getEngine();

		engine.setOnAlert(new EventHandler<WebEvent<String>>() {

			@Override
			public void handle(WebEvent<String> event) {
				
				System.out.println(event.getData());
					
			}
		});

		engine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {

			@Override
			public void changed(ObservableValue<? extends State> observable, State oldValue, State newValue) {
				engine.setJavaScriptEnabled(true);
				JSObject window = (JSObject) engine.executeScript("window");
				window.setMember("jfxCallback", new JFXCallback());
			}
		});
		
		

		engine.load(getClass().getResource("map.html").toString());

		contentPane.getChildren().add(view);

		ImageView content = new ImageView(getClass().getResource("content.png").toString());
		content.setEffect(new DropShadow());
		content.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				showAllIcons(contentPane);

			}
		});
		setupSettingsAnimation(contentPane);
		contentPane.getChildren().add(content);
		content.setTranslateX(-20);
		content.setTranslateY(-20);
		StackPane.setAlignment(content, Pos.BOTTOM_RIGHT);

		addOverviewPanel();

		Scene scene = new Scene(contentPane, 1000, 800);
		scene.getStylesheets().add(getClass().getResource("style.css").toString());

		stage.setScene(scene);
		stage.show();

	}

	private void addOverviewPanel() {

		HBox box = new HBox(20);
		box.setMaxSize(650, 100);
		box.setId("detail-box");

		Text text = new Text("Cyklat");
		text.setId("text");
		box.getChildren().add(text);

		text = new Text("Kvar");
		text.setId("text");
		box.getChildren().add(text);

		text = new Text("Total hšjd");
		text.setId("text");
		box.getChildren().add(text);

		text = new Text("Hastighet");
		text.setId("text");
		box.getChildren().add(text);

		text = new Text("Snitt dist");
		text.setId("text");
		box.getChildren().add(text);

		text = new Text("Punka");
		text.setId("text");
		box.getChildren().add(text);

		contentPane.getChildren().add(box);
		StackPane.setAlignment(box, Pos.TOP_CENTER);

	}

	private void setupSettingsAnimation(StackPane pane) {

		for (int i = 0; i < menu.length; i++) {

			final ImageView imageView = new ImageView(menu[i]);
			imageView.setVisible(false);

			imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {

					slideInPanel("martin");

				}
			});

			imageView.setTranslateX(-20);
			imageView.setTranslateY(-20);
			StackPane.setAlignment(imageView, Pos.BOTTOM_RIGHT);
			pane.getChildren().add(imageView);
			TranslateTransition translate = new TranslateTransition(Duration.millis(150), imageView);

			transitions.add(translate);
			translate.setByX(125 * i);
			parallelTransition.getChildren().add(translate);

		}

	}

	private void slideInPanel(String what) {

		infoPane.setStyle("-fx-background-color: red;");
		infoPane.setMaxSize(600, 400);
		infoPane.setTranslateX(-800);

		contentPane.getChildren().add(infoPane);

		TranslateTransition translate = new TranslateTransition(Duration.millis(400), infoPane);
		translate.setToX(0);
		translate.play();

	}

	private void slideOutPanel(String what) {

		TranslateTransition translate = new TranslateTransition(Duration.millis(400), infoPane);
		translate.setToX(800);
		translate.play();

	}

	private void showAllIcons(StackPane pane) {

		for (TranslateTransition transition : transitions) {
			transition.getNode().setVisible(true);
			double delta = -1 * transition.getByX();
			transition.setByX(delta);
		}

		parallelTransition.play();

	}

	public class JFXCallback {

		public void openImage(String image) {
			System.out.println(image);
			BorderedImage imageView = new BorderedImage(new ImageView(image));
			contentPane.getChildren().add(imageView);
		}

	}

}
