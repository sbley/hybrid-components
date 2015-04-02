package de.saxsys.webview;

import javafx.beans.value.ChangeListener;
import javafx.concurrent.Worker;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class ChartView extends VBox {

    private WebView webView = new WebView();
    private WebEngine webEngine = webView.getEngine();
    private TextField txtTodo = new TextField("4");
    private TextField txtInProgress = new TextField("5");
    private TextField txtDone = new TextField("6");

    public ChartView() {
        initWebEngine();
        initUI();
    }

    private void initWebEngine() {
        webEngine.getLoadWorker()
                .stateProperty()
                .addListener((ChangeListener<Worker.State>) (ov, oldState, newState) -> {
                    if (Worker.State.SUCCEEDED == newState) {
                    }
                });
        loadHtml(webEngine, "/client.html");
    }

    private void loadHtml(WebEngine webEngine, String file) {
        webEngine.load(getClass().getResource(file).toExternalForm());
    }

    private void initUI() {
        setStyle("-fx-background-color:white;");
        setAlignment(Pos.CENTER);
        getChildren().addAll(webView, txtTodo, txtInProgress, txtDone);

        ChangeListener<String> changeListener =
                (observable, oldVal, newVal) -> {
                    int todo = nullsafeVal(txtTodo.getText());
                    int inProgress = nullsafeVal(txtInProgress.getText());
                    int done = nullsafeVal(txtDone.getText());
                    webEngine.executeScript("document.querySelector('pie-chart').todo = " + todo);
                    webEngine.executeScript("document.querySelector('pie-chart').inProgress = "
                            + inProgress);
                    webEngine.executeScript("document.querySelector('pie-chart').done = " + done);
                };
        txtTodo.textProperty().addListener(changeListener);
        txtInProgress.textProperty().addListener(changeListener);
        txtDone.textProperty().addListener(changeListener);
    }

    private int nullsafeVal(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
