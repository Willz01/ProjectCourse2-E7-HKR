package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import se.hkr.e7.Singleton;

public class AnalyserDashboard extends Controller {
    public Button lineChart;
    public Button edit;
    public Button viewResultsButton;
    public Button Bar;
    public Button pieChart;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/AnalyserDashboard.fxml");

        edit.setOnAction(actionEvent -> loadScene("view/ChangeInfo.fxml"));
        viewResultsButton.setOnAction(actionEvent -> loadScene("view/ViewResultsAnalyser.fxml"));
        lineChart.setOnAction(actionEvent -> loadScene("view/AnalyserLineChart.fxml"));
        Bar.setOnAction(actionEvent -> loadScene("view/AnalyserBarChart.fxml"));
        pieChart.setOnAction(actionEvent -> loadScene("view/AnalyserPieChart.fxml"));
    }

}
