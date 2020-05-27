package se.hkr.e7.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ToggleButton;
import se.hkr.e7.DatabaseHandler;
import se.hkr.e7.Singleton;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AnalyserLineChartController extends Controller {
    public LineChart<String, Number> lineChart;
    public CategoryAxis xAxis;
    public ToggleButton positiveButton;
    public ToggleButton negativeButton;
    public ToggleButton pendingButton;
    private List<Object[]> positiveResults;
    private List<Object[]> negativeResults;
    private List<Object[]> pendingResults;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/AnalyserLineChart.fxml");
        lineChart.setTitle("Results chart");

        positiveResults = DatabaseHandler.query("SELECT DATE(R.dateTime), COUNT(R.id) FROM Result R WHERE R.status='0' " +
                "AND DATE(R.dateTime) >= CURDATE() - 30 GROUP BY DATE(R.dateTime)");
        negativeResults = DatabaseHandler.query("SELECT DATE(R.dateTime), COUNT(R.id) FROM Result R WHERE R.status='1' " +
                "AND DATE(R.dateTime) >= CURDATE() - 30 GROUP BY DATE(R.dateTime)");
        pendingResults = DatabaseHandler.query("SELECT DATE(R.dateTime), COUNT(R.id) FROM Result R WHERE R.status='2' " +
                "AND DATE(R.dateTime) >= CURDATE() - 30 GROUP BY DATE(R.dateTime)");

        ArrayList<String> dates = new ArrayList<>();
        for (int i = 30; i >= 0; i--) {
            dates.add(LocalDateTime.now().minusDays(i).format(DateTimeFormatter.ISO_DATE));
        }
        xAxis.setCategories(FXCollections.observableList(dates));

        showData();
        for (ToggleButton toggleButton : new ToggleButton[]{positiveButton, negativeButton, pendingButton}) {
            toggleButton.setOnAction(actionEvent -> showData());
        }
    }

    private void showData() {
        lineChart.getData().clear();
        if (negativeButton.isSelected()) {
            lineChart.getData().add(generateSeries(negativeResults, "Negative results"));
        }

        if (pendingButton.isSelected()) {
            lineChart.getData().add(generateSeries(pendingResults, "Pending results"));
        }

        if (positiveButton.isSelected()) {
            lineChart.getData().add(generateSeries(positiveResults, "Positive results"));
        }
    }

    private XYChart.Series<String, Number> generateSeries(List<Object[]> rows, String seriesName) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(seriesName);

        for (Object[] row : rows) {
            series.getData().add(new XYChart.Data<>(row[0].toString(), (Number) row[1]));
        }

        return series;
    }
}
