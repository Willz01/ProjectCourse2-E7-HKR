package se.hkr.e7.controller;

import javafx.fxml.FXML;
import se.hkr.e7.model.Singleton;

public class AnalyserDashboardController extends Controller {

    @FXML
    public void initialize() {
        Singleton.getInstance().addSceneHistory("view/AnalyserDashboard.fxml");
    }
}