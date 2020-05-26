package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import se.hkr.e7.DatabaseHandler;
import se.hkr.e7.Singleton;

import java.util.List;

public class AnalyserDashboardController extends Controller {

    public LineChart<String, String> lineChart;
    public CategoryAxis xAxis;
    public CategoryAxis yAxis;
    public Button showBtn;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/AnalyserDashboard.fxml");

        List<Object[]> positive = DatabaseHandler.query("SELECT COUNT(R.id), DATE(R.dateTime) AS date_only FROM Result R where R.status='0' GROUP BY date_only");
        List<Object[]> negative = DatabaseHandler.query("SELECT COUNT(R.id), DATE(R.dateTime) AS date_only FROM Result R where R.status='1' GROUP BY date_only");
        List<Object[]> pending = DatabaseHandler.query("SELECT COUNT(R.id), DATE(R.dateTime) AS date_only FROM Result R where R.status='2' GROUP BY date_only");

        XYChart.Series<String, String> seriesPositive = new XYChart.Series<>();
        XYChart.Series<String, String> seriesNegative = new XYChart.Series<>();
        XYChart.Series<String, String> seriesPending = new XYChart.Series<>();

        seriesPositive.setName("Positive Results");
        seriesNegative.setName("Negative Results");
        seriesPending.setName("Pending Results");

        lineChart.setTitle("Results chart");
        xAxis.setLabel("Date");
        yAxis.setLabel("Cases");

        for (Object line : negative) {
            Object[] row = (Object[]) line;
            seriesNegative.getData().add(new XYChart.Data<>(row[1].toString(), row[0].toString()));
        }

        for (Object line : positive) {
            Object[] row = (Object[]) line;
            seriesPositive.getData().add(new XYChart.Data<>(row[1].toString(), row[0].toString()));
        }

        for (Object line : pending) {
            Object[] row = (Object[]) line;
            seriesPending.getData().add(new XYChart.Data<>(row[1].toString(), row[0].toString()));
        }

        lineChart.getData().add(seriesPositive);
        lineChart.getData().add(seriesNegative);
        lineChart.getData().add(seriesPending);

    }
}