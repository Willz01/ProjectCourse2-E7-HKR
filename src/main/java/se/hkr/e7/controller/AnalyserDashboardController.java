package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.*;
import se.hkr.e7.model.Singleton;

import java.util.concurrent.ThreadLocalRandom;

public class AnalyserDashboardController extends Controller {

    public LineChart<Number, Number> lineChart;
    public NumberAxis xAxis;
    public NumberAxis yAxis;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/AnalyserDashboard.fxml");

        lineChart.setTitle("Line chart");
        xAxis.setLabel("xAxis");
        yAxis.setLabel("yAxis");

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Random numbers");

        for (int i = 0; i < 20; i++) {
            series.getData().add(new XYChart.Data<>(i, ThreadLocalRandom.current().nextInt(100)));
        }

        lineChart.getData().add(series);
    }
}