package de.saxsys.webview.login;

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

    public class LoginService {
        public void login(String username, String password) {
            txtLogin.textProperty().set(username);
        }
    }

    WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();
    Label lblLogin = new Label("Login: ");
    TextField txtLogin = new TextField();

    public LoginView() {
        webEngine.load(getClass().getResource("login.html").toExternalForm());

        JSObject jsobj = (JSObject) webEngine.executeScript("window");
        jsobj.setMember("loginService", new LoginService());
        initUI();
    }

    private void initUI() {
        setStyle("-fx-background-color:white;");
        setAlignment(Pos.CENTER);
        Rectangle rectangle = new Rectangle(500, 450);
        webView.setClip(rectangle);
        getChildren().addAll(webView, new HBox(lblLogin, txtLogin));
    }
}
