package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import se.hkr.e7.DatabaseHandler;
import se.hkr.e7.Singleton;
import se.hkr.e7.model.Result;

import java.util.Date;
import java.util.List;

public class AnalyserDashboardController extends Controller {

    public LineChart<Number, Number> lineChart;
    public NumberAxis xAxis;
    public NumberAxis yAxis;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/AnalyserDashboard.fxml");

        List<Result> results = DatabaseHandler.loadAll(Result.class);

// Create a data series
        XYChart.Series series = new XYChart.Series<Integer, Integer>();
        series.setName("Results");

        lineChart.setTitle("Line chart");
        xAxis.setLabel("xAxis");
        yAxis.setLabel("yAxis");

        series.setName("Random numbers");

        int i = 0;
        for (Result result : results) {
            i++;
            series.getData().add(new XYChart.Data<>(result.getDateTime().getYear(), i));
        }

        lineChart.getData().add(series);
    }
}