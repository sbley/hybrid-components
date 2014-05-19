package de.saxsys.firstapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MyFirstApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		ColorPicker color = new ColorPicker();
		Rectangle rectangle = new Rectangle(50, 50);

		VBox vBox = new VBox();
		vBox.getChildren().addAll(color, rectangle);

		rectangle.widthProperty().bind(color.widthProperty().multiply(2));

		rectangle.fillProperty().bind(color.valueProperty());

		Scene scene = new Scene(vBox, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
