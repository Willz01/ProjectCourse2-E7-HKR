package se.hkr.e7.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import se.hkr.e7.DatabaseHandler;
import se.hkr.e7.Singleton;

import java.math.BigInteger;
import java.util.List;

public class AnalyserPieChartController {
    public PieChart pieChart;

    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/AnalyserPieChart.fxml");

        pieChart.setTitle("Result status type distribution");
        List positiveResults = DatabaseHandler.sqlQuery("select count(status) from result where status = 0");
        List negativeResults = DatabaseHandler.sqlQuery("select count(status) from result where status = 1");
        List pendingResults = DatabaseHandler.sqlQuery("select count(status) from result where status = 2");

        BigInteger positive = (BigInteger) positiveResults.get(0);
        BigInteger negative = (BigInteger) negativeResults.get(0);
        BigInteger pending = (BigInteger) pendingResults.get(0);

        double p, n, pen;
        p = positive.doubleValue();
        n = negative.doubleValue();
        pen = pending.doubleValue();

        ObservableList<PieChart.Data> dataObservableList = FXCollections.observableArrayList(
                new PieChart.Data("Positive", p),
                new PieChart.Data("Negative", n),
                new PieChart.Data("Pending", pen)
        );
        pieChart.setData(dataObservableList);

    }
}
