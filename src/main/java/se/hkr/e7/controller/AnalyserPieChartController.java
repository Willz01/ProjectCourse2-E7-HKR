package se.hkr.e7.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import se.hkr.e7.DatabaseHandler;
import se.hkr.e7.Singleton;

import java.util.List;

public class AnalyserPieChartController {
    public PieChart pieChart;

    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/AnalyserPieChart.fxml");

        pieChart.setTitle("Result status type distribution");
        List<Object[]> results = DatabaseHandler.query("select status,count(status) from Result group by status");

        PieChart.Data[] p = new PieChart.Data[results.size()];
        for (int i = 0; i < results.size(); i++) {
            p[i] = new PieChart.Data((results.get(i)[0]).toString(), ((Long) results.get(i)[1]).doubleValue());
        }


        ObservableList<PieChart.Data> dataObservableList = FXCollections.observableArrayList(p);
        pieChart.setData(dataObservableList);

    }
}
