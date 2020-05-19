package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import se.hkr.e7.DatabaseHandler;
import se.hkr.e7.Singleton;

import java.util.List;

public class AnalyserDashboardController extends Controller {

    public LineChart<String, String> lineChart;
    public CategoryAxis xAxis;
    public CategoryAxis yAxis;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/AnalyserDashboard.fxml");


        List positive = DatabaseHandler.query("SELECT COUNT(R.id), DATE(R.dateTime) AS date_only FROM Result R where R.status='0' GROUP BY date_only");
        List negative = DatabaseHandler.query("SELECT COUNT(R.id), DATE(R.dateTime) AS date_only FROM Result R where R.status='1' GROUP BY date_only");
        List pending = DatabaseHandler.query("SELECT COUNT(R.id), DATE(R.dateTime) AS date_only FROM Result R where R.status='2' GROUP BY date_only");


// Create a data series
        XYChart.Series<String, String> seriesPositive = new XYChart.Series<>();
        XYChart.Series<String, String> seriesNegative = new XYChart.Series<>();
        XYChart.Series<String, String> seriesPending = new XYChart.Series<>();

        seriesPositive.setName("Positive Results");
        seriesNegative.setName("Negative Results");
        seriesPending.setName("Pending Results");

        lineChart.setTitle("Results chart");
        xAxis.setLabel("Date");
        yAxis.setLabel("Cases");

        //          adding the negative result.
        for (Object line : negative) {
            Object[] row = (Object[]) line;
            seriesNegative.getData().add(new XYChart.Data<>(row[1].toString(), row[0].toString()));
        }

        //          adding the positive result.
        for (Object line : positive) {
            Object[] row = (Object[]) line;
            seriesPositive.getData().add(new XYChart.Data<>(row[1].toString(), row[0].toString()));
        }
        //        adding the pending result.
        for (Object line : pending) {
            Object[] row = (Object[]) line;
            seriesPending.getData().add(new XYChart.Data<>(row[1].toString(), row[0].toString()));
        }

        lineChart.getData().add(seriesPositive);
        lineChart.getData().add(seriesNegative);
        lineChart.getData().add(seriesPending);
    }
}