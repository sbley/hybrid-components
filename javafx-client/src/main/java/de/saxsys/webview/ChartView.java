package de.saxsys.webview;

import javafx.beans.value.ChangeListener;
import javafx.concurrent.Worker;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

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
                .addListener(
                        (ChangeListener<Worker.State>) (ov, oldState, newState) -> {
                            if (Worker.State.SUCCEEDED == newState) {
                                JSObject ar = (JSObject) webEngine.executeScript("[1,2]");
                                Object slot = ar.getSlot(0);
                                JSObject jsobj = (JSObject) webEngine.executeScript("window");
                                JSObject s =
                                        (JSObject) webEngine.executeScript("window.getComputedStyle(document.getElementById('chart-area', null));");
                                // jsobj.setMember("loginService", new LoginService());
                                System.out.println(s);
                            }
                        });
        loadHtml(webEngine, "/app/chart/chart.html");
    }

    private void loadHtml(WebEngine webEngine, String file) {
        webEngine.load(getClass().getResource(file).toExternalForm());
    }

    private void initUI() {
        setStyle("-fx-background-color:white;");
        setAlignment(Pos.CENTER);
        // webView.setClip(new Rectangle(700, 550));
        getChildren().addAll(webView, txtTodo, txtInProgress, txtDone);

        ChangeListener<String> changeListener =
                (observable, oldVal, newVal) -> {
                    int todo = nullsafeVal(txtTodo.getText());
                    int inProgress = nullsafeVal(txtInProgress.getText());
                    int done = nullsafeVal(txtDone.getText());
                    webEngine.executeScript(String.format("updateValues(%d,%d,%d);", todo,
                            inProgress, done));
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
