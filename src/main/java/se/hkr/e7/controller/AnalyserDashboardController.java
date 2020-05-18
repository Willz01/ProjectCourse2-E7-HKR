package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import se.hkr.e7.DatabaseHandler;
import se.hkr.e7.Singleton;
import se.hkr.e7.model.Result;

import java.sql.Date;
import java.util.List;

public class AnalyserDashboardController extends Controller {

    public LineChart<Date, Integer> lineChart;
    public CategoryAxis xAxis;
    public NumberAxis yAxis;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/AnalyserDashboard.fxml");

        List<Result> results = DatabaseHandler.loadAll(Result.class);

        List list = DatabaseHandler.query("SELECT COUNT(R.id), DATE(R.dateTime) AS date_only FROM Result R GROUP BY date_only");


// Create a data series
        XYChart.Series series = new XYChart.Series<Date, Integer>();
        series.setName("Results");

        lineChart.setTitle("Line chart");
        xAxis.setLabel("xAxis");
        yAxis.setLabel("yAxis");

        series.setName("Random numbers");

        for (Object item : list) {
            Object[] row = (Object[]) item;
            System.out.println(row[1] + "  " + row[0]);

            series.getData().add(new XYChart.Data<>(row[1], row[0]));

        }

        lineChart.getData().add(series);
    }
}