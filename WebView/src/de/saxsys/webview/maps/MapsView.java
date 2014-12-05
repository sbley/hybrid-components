package de.saxsys.webview.maps;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

public class MapsView extends VBox {

    WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();
    Slider slider = new Slider();

    public MapsView() {

        webEngine.load(getClass().getResource("demo.html").toExternalForm());

        JSObject jsobj = (JSObject) webEngine.executeScript("window");
        jsobj.setMember("java", new MapsCallback());

        slider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
                    Boolean newValue) {
                if (oldValue == true && newValue == false) {
                    // @formatter:off
                    webEngine.executeScript(""
                            + "var map = new google.maps.Map(document.getElementById(\"mapcanvas\"), Options);"
                            + "map.setZoom(" + (int) slider.getValue() + ");"
                            + "google.maps.event.addListener(map, 'zoom_changed', function() {"
                            + "var zoomLevel = map.getZoom();" + "java.zoomChanged(zoomLevel);"
                            + "});");
                    // @formatter:on
                }
            }
        });
        initUI();
    }

    protected class MapsCallback {
        public void zoomChanged(double zoom) {
            System.out.println("Zoom Changed: " + zoom);
            slider.setValue(zoom);
        }
    }

    private void initUI() {
        slider.setMax(21);
        slider.setValue(10);
        slider.setMinWidth(300);
        slider.setShowTickMarks(true);
        slider.setScaleX(1.5);
        slider.setScaleY(1.5);
        slider.setTranslateY(-30);
        setStyle("-fx-background-color:white;");
        setAlignment(Pos.CENTER);
        Rectangle rectangle = new Rectangle(480, 480);
        webView.setClip(rectangle);
        getChildren().addAll(webView, new Group(slider));
    }
}
