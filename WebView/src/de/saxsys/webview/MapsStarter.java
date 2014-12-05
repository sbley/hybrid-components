package de.saxsys.webview;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import de.saxsys.webview.login.LoginView;

public class MapsStarter extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new LoginView(), 500, 550);
        stage.setScene(scene);
        // stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
}
