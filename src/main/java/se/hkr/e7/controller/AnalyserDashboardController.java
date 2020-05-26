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

public class AnalyserDashboardController extends Controller {

    public LineChart<String, Number> lineChart;
    public CategoryAxis xAxis;
    public ToggleButton positiveButton;
    public ToggleButton negativeButton;
    public ToggleButton pendingButton;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/AnalyserDashboard.fxml");

        lineChart.setTitle("Results chart");

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
            addSeries(DatabaseHandler.query("SELECT DATE(R.dateTime), COUNT(R.id) FROM Result R WHERE R.status='1' " +
                    "AND DATE(R.dateTime) >= CURDATE() - 30 GROUP BY DATE(R.dateTime)"), "Negative results");
        }

        if (pendingButton.isSelected()) {
            addSeries(DatabaseHandler.query("SELECT DATE(R.dateTime), COUNT(R.id) FROM Result R WHERE R.status='2' " +
                    "AND DATE(R.dateTime) >= CURDATE() - 30 GROUP BY DATE(R.dateTime)"), "Pending results");
        }

        if (positiveButton.isSelected()) {
            addSeries(DatabaseHandler.query("SELECT DATE(R.dateTime), COUNT(R.id) FROM Result R WHERE R.status='0' " +
                    "AND DATE(R.dateTime) >= CURDATE() - 30 GROUP BY DATE(R.dateTime)"), "Positive results");
        }
    }

    private void addSeries(List<Object[]> rows, String seriesName) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(seriesName);

        for (Object[] row : rows) {
            series.getData().add(new XYChart.Data<>(row[0].toString(), (Number) row[1]));
        }

        lineChart.getData().add(series);
    }
}