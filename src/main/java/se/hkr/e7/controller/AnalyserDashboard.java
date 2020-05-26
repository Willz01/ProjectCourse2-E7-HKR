package se.hkr.e7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import se.hkr.e7.Singleton;

public class AnalyserDashboard extends Controller {
    public Button lineChart;
    public Button edit;

    public Button viewResultsButton;

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/AnalyserDashboard.fxml");
        edit.setOnAction(actionEvent -> loadScene("view/ChangeInfo.fxml", actionEvent));
        viewResultsButton.setOnAction(actionEvent -> loadScene("view/ViewResultsAnalyser.fxml", actionEvent));
        lineChart.setOnAction(actionEvent -> loadScene("view/AnalyserLineChart.fxml", actionEvent));

    }

}
    