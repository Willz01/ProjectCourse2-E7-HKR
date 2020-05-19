package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import se.hkr.e7.DatabaseHandler;
import se.hkr.e7.Singleton;
import se.hkr.e7.model.Result;

import java.util.List;

public class AnalyserDashboardController extends Controller {

    public LineChart<String, String> lineChart;
    public CategoryAxis xAxis;
    public CategoryAxis yAxis;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/AnalyserDashboard.fxml");


        List list = DatabaseHandler.query("SELECT COUNT(R.id), DATE(R.dateTime) AS date_only FROM Result R GROUP BY date_only");


// Create a data series
        XYChart.Series<String, String> series = new XYChart.Series<>();
        series.setName("Results");

        lineChart.setTitle("Line chart");
        xAxis.setLabel("Date");
        yAxis.setLabel("Cases");

        series.setName("All test");

        for (Object line : list) {
            Object[] row = (Object[]) line;
            series.getData().add(new XYChart.Data<>(row[1].toString(), row[0].toString()));
        }

        lineChart.getData().add(series);
    }
}