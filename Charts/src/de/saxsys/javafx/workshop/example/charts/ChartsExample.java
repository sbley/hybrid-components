package de.saxsys.javafx.workshop.example.charts;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Example application that demonstrates how charts can be used and dynamically
 * updated.
 */
public class ChartsExample extends Application {

	@Override
	public void start(final Stage stage) throws Exception {
		final Pane formular = FXMLLoader.load(ChartsExample.class
				.getResource("charts.fxml"));

		final Scene scene = new Scene(formular);
		stage.setScene(scene);
		stage.show();

//		final String modena = ChartsExample.class.getResource("modena.css")
//				.toExternalForm();
//		scene.getStylesheets().add(modena);
	}

	public static void main(final String[] args) {
		launch(args);
	}

}
