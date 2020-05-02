package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import se.hkr.e7.model.Singleton;

public class AnalyserDashboardController extends Controller {

    final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();

    @FXML
    final LineChart<Number, Number> chart =
            new LineChart<Number, Number>(xAxis, yAxis);
    XYChart.Series series = new XYChart.Series();

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/AnalyserDashboard.fxml");


        Stage stage = new Stage();
        stage.setTitle("Line Chart Sample");

        xAxis.setLabel("Number of Month");
        //creating the chart


        chart.setTitle(" Monitoring ");
        //defining a series

        series.setName("My portfolio");
        //populating the series with data
        series.getData().add(new XYChart.Data(87, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));

        Scene scene = new Scene(chart, 800, 600);
        chart.getData().add(series);

        stage.setScene(scene);
        stage.show();
    }
}