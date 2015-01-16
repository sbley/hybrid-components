package de.saxsys.webview.login;

import javafx.beans.value.ChangeListener;
import javafx.concurrent.Worker.State;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

public class LoginView extends VBox {

    private WebView webView = new WebView();
    private WebEngine webEngine = webView.getEngine();
    private Label lblLogin = new Label("Login: ");
    private TextField txtLogin = new TextField();

    public LoginView() {
        initWebEngine();
        initUI();
    }

    private void initWebEngine() {
        webEngine.getLoadWorker()
                .stateProperty()
                .addListener((ChangeListener<State>) (ov, oldState, newState) -> {
                    if (State.SUCCEEDED == newState) {
                        JSObject jsobj = (JSObject) webEngine.executeScript("window");
                        jsobj.setMember("loginService", new LoginService());
                    }
                });
        webEngine.load(getClass().getResource("login.html").toExternalForm());
    }

    private void initUI() {
        setStyle("-fx-background-color:white;");
        setAlignment(Pos.CENTER);
        webView.setClip(new Rectangle(500, 450));
        getChildren().addAll(webView, new HBox(lblLogin, txtLogin));
    }

    /** Handle login request */
    public class LoginService {
        public void login(String username, String password) {
            txtLogin.textProperty().set(username);
        }
    }
}
