package de.saxsys.jax;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import de.saxsys.jax.captcha.Captcha;

public class Starter extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		StackPane root = new StackPane();
		Captcha captcha = new Captcha();

		captcha.setSummand1(5);
		captcha.setSummand2(10);
		System.out.println(captcha.getSum());

		captcha.setScaleX(3.0);
		captcha.setScaleY(3.0);

		Group group = new Group();
		group.getChildren().add(captcha);

		root.getChildren().add(group);

		Scene rootScene = new Scene(root);
		primaryStage.setScene(rootScene);
		primaryStage.show();

	}

}
