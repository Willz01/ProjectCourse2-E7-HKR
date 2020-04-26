package se.hkr.e7.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

import static se.hkr.e7.model.Location.*;


public class ViewResultsController extends Controller {

    public void Back(ActionEvent actionEvent) throws IOException {
       loadScene("view/AdminDashboard.fxml",actionEvent);
    }


    public void Exit() {
        System.exit(0);
    }

    @FXML
    public void initialize() {

    }


}
