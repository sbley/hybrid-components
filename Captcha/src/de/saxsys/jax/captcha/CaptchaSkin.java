package de.saxsys.jax.captcha;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CaptchaSkin extends VBox implements Skin<Captcha> {

	private final Captcha control;

	private final Label captchaQuestion = new Label("1+1");
	private final TextField captchaInput = new TextField();
	private final Button okButton = new Button("Ok");

	public CaptchaSkin(Captcha control) {
		this.control = control;

		setSpacing(10);
		setPadding(new Insets(10));
		setAlignment(Pos.CENTER);
		captchaInput.setPromptText("Solution");

		// @formatter:on

		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */

		Captcha controlCaptcha = getSkinnable();

		captchaQuestion.textProperty().bind(
				controlCaptcha.summand1Property().asString().concat("+")
						.concat(controlCaptcha.summand2Property().asString()));

		okButton.disableProperty().bind(
				captchaInput.textProperty().isNotEqualTo(
						controlCaptcha.sumProperty().asString()));

		// Captcha skinnable = getSkinnable();

		// captchaQuestion.textProperty().bind(
		// Bindings.concat(skinnable.summand1Property(), "+",
		// skinnable.summand2Property()));
		//
		// okButton.disableProperty().bind(
		// captchaInput.textProperty().isNotEqualTo(
		// skinnable.sumProperty().asString()));

		getChildren().addAll(captchaQuestion, captchaInput, okButton);
	}

	@Override
	public Captcha getSkinnable() {
		return control;
	}

	@Override
	public Node getNode() {
		return this;
	}

	@Override
	public void dispose() {

	}

}
