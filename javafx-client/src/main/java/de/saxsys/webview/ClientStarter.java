package de.saxsys.webview;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientStarter extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new ChartView(), 700, 650);
        stage.setScene(scene);
        stage.show();
    }
}
