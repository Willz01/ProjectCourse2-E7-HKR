package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class AnalyserDashboardController extends Controller {
    public Button Back;
    public Button Exit;

    public void Back(ActionEvent actionEvent) throws IOException {
        loadScene("view/staffLogin.fxml",actionEvent);
    }

    public void Exit() {
        System.exit(0);
    }
}