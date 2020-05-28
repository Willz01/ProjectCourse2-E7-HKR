package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import se.hkr.e7.DatabaseHandler;
import se.hkr.e7.Singleton;
import se.hkr.e7.model.Result;

import java.util.List;

public class AnalyserBarChartController {
    @FXML
    public NumberAxis y;
    @FXML
    public CategoryAxis x;
    @FXML
    public ToggleGroup dateRange;
    @FXML
    private BarChart<String, Number> BarChart;
    @FXML
    private List sevenDays;
    @FXML
    private List month;
    @FXML
    private List sixMonths;


    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/AnalyserDashboard.fxml");
        BarChart.setTitle("Results chart");


        sevenDays = DatabaseHandler.sqlQuery("SELECT R.status, COUNT(R.id) FROM Result R WHERE DATE(R.dateTime) >= " +
                "date_sub(curdate(),interval 7 day) group by R.status");

        month = DatabaseHandler.sqlQuery("SELECT R.status, COUNT(R.id) FROM Result R WHERE DATE(R.dateTime) >= " +
                "date_sub(curdate(),interval 30 day) group by R.status");

        sixMonths = DatabaseHandler.sqlQuery("SELECT R.status, COUNT(R.id) FROM Result R WHERE DATE(R.dateTime) >= " +
                "date_sub(curdate(),interval 180 day) group by R.status");

        showData();

        dateRange.selectedToggleProperty().addListener(observable -> showData());

    }


    private void showData() {
        BarChart.getData().clear();

        RadioButton radioButton = (RadioButton) dateRange.getSelectedToggle();

        switch (radioButton.getId()) {
            case "week":
                BarChart.getData().add(generateSeries(sevenDays, "Past 7 Days results"));
                break;
            case "month":
                BarChart.getData().add(generateSeries(month, "Past 30 Days results"));
                break;
            case "six":
                BarChart.getData().add(generateSeries(sixMonths, "Past 180 Days results"));
                break;

        }

    }


    private XYChart.Series<String, Number> generateSeries(List<Object[]> rows, String seriesName) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(seriesName);

        for (Object[] row : rows) {

            Result.Status status;

            switch ((Integer) row[0]) {
                case 0:
                    status = Result.Status.POSITIVE;
                    break;
                case 1:
                    status = Result.Status.NEGATIVE;
                    break;
                default:
                    status = Result.Status.PENDING;
            }

            series.getData().add(new XYChart.Data<>(status.toString(), (Number) row[1]));
        }

        return series;
    }
}