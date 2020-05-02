package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import se.hkr.e7.model.DatabaseHandler;
import se.hkr.e7.model.Result;
import se.hkr.e7.model.Singleton;

import java.util.List;

public class AnalyserDashboardController extends Controller {

    final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis();

    @FXML
    final LineChart<Number, Number> chart =
            new LineChart<Number, Number>(xAxis, yAxis);
    XYChart.Series positiveSeries = new XYChart.Series();
    XYChart.Series negativeSeries = new XYChart.Series();

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/AnalyserDashboard.fxml");

        Stage stage = new Stage();
        stage.setTitle("Line Chart Sample");
        xAxis.setLabel("Number of Month");
        //creating the chart
        chart.setTitle(" Monitoring ");
        //defining a series

        List<Result> results = DatabaseHandler.loadAll(Result.class);
        int counter = 0;

        for (Result result : results) {
            if (result.getStatus() == Result.Status.POSITIVE) {
                counter++;

                positiveSeries.getData().add(new XYChart.Data( 12312313, counter));

            }
        }

//        for (Result result : results) {
//            if (result.getStatus() == Result.Status.NEGATIVE) {
//                counter++;
//
//                negativeSeries.getData().add(new XYChart.Data(54, 2));
//                negativeSeries.getData().add(new XYChart.Data(4, 445));
//                negativeSeries.getData().add(new XYChart.Data(65, 345));
//            }
//        }

        positiveSeries.setName("My portfolio");
//        populating the series with data
//        positiveSeries.getData().add(new XYChart.Data(7,6));
//        positiveSeries.getData().add(new XYChart.Data(2, 14));
//        positiveSeries.getData().add(new XYChart.Data(3, 15));
//        positiveSeries.getData().add(new XYChart.Data(4, 24));
//        positiveSeries.getData().add(new XYChart.Data(5, 34));
//        positiveSeries.getData().add(new XYChart.Data(6, 36));
//        positiveSeries.getData().add(new XYChart.Data(7, 22));
//        positiveSeries.getData().add(new XYChart.Data(8, 45));
//        positiveSeries.getData().add(new XYChart.Data(9, 43));
//        positiveSeries.getData().add(new XYChart.Data(10, 17));
//        positiveSeries.getData().add(new XYChart.Data(11, 29));
//        positiveSeries.getData().add(new XYChart.Data(12, 25));

        Scene scene = new Scene(chart, 800, 600);
        chart.getData().add(positiveSeries);
        stage.setScene(scene);
        stage.show();
    }
}